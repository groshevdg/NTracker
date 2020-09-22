package ru.groshevdg.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import ru.groshevdg.R
import ru.groshevdg.di.components.DaggerFragmentComponent
import ru.groshevdg.di.factory.ViewModelFactory
import ru.groshevdg.misc.ItemSpaceDecorator
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.navigation.Navigator
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.ui.news.adapters.NewsListRecyclerAdapter
import javax.inject.Inject

class NewsListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private val adapter = NewsListRecyclerAdapter()
    private lateinit var layoutManager: LinearLayoutManager
    @Inject lateinit var factory: ViewModelFactory
    @Inject lateinit var navigator: Navigator
    private val viewModel: NewsListViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = DaggerFragmentComponent.builder()
            .activityComponent((activity as ApplicationActivity).activityComponent)
            .build()

        fragmentComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container, false)

        view.fnlRefreshLayout.setOnRefreshListener(this)
        setupRecyclerView(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialLoadData()
    }

    private fun setupRecyclerView(view: View) {
        layoutManager = LinearLayoutManager(view.context)
        view.apply {
            fnlNewsRecyclerView.adapter = adapter
            fnlNewsRecyclerView.layoutManager = layoutManager
            fnlNewsRecyclerView.addItemDecoration(
                ItemSpaceDecorator(
                    marginTopInDp = 8,
                    marginRightInDp = 0,
                    marginBottomInDp = 16,
                    marginLeftInDp = 0
                )
            )
        }
    }

    private fun initialLoadData() {
        viewModel.loadNews()
        fnlProgressBar.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        viewModel.newsLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                showLoadedNews(it)
            } else {
                showEmptyErrorMessage()
            }
        }
    }

    private fun showLoadedNews(it: List<NewsListItems>) {
        adapter.setItems(it)
        if (navigator.isUserLeftSettingsFragment) {
            fnlNewsRecyclerView.visibility = View.GONE
            fnlProgressBar.visibility = View.VISIBLE
            navigator.isUserLeftSettingsFragment = false
        }
        else {
            fnlNewsRecyclerView.visibility = View.VISIBLE
            fnlProgressBar.visibility = View.GONE
            fnlRefreshLayout.isRefreshing = false
            fnlEmptySourceListTextView.visibility = View.GONE
        }
    }

    override fun onRefresh() {
        viewModel.loadNews()
    }

    private fun showEmptyErrorMessage() {
        fnlEmptySourceListTextView.visibility = View.VISIBLE
        fnlNewsRecyclerView.visibility = View.GONE
        fnlProgressBar.visibility = View.GONE
    }
}