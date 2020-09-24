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
import ru.groshevdg.ui.news.viewHolders.OnChannelClickedListener
import ru.groshevdg.ui.news.viewHolders.OnNewClickListener
import javax.inject.Inject

class NewsListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
    OnNewClickListener, OnChannelClickedListener {

    private val adapter = NewsListRecyclerAdapter()
    private lateinit var layoutManager: LinearLayoutManager
    @Inject lateinit var factory: ViewModelFactory
    @Inject lateinit var navigator: Navigator
    private val viewModel: NewsListViewModel by viewModels { factory }

    companion object {
        private const val IS_LOADED_KEY = "isLoaded"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = DaggerFragmentComponent.builder()
            .activityComponent((activity as ApplicationActivity).activityComponent)
            .build()

        fragmentComponent.inject(this)

        // update link after recreate fragment while rotation
        navigator.newsFragment = this
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
        if (!viewModel.isLoaded || navigator.isUserLeftSettingsFragment) {
            initialLoadData()
            showProgress()
        } else if (savedInstanceState != null && !savedInstanceState.getBoolean(IS_LOADED_KEY)) {
            initialLoadData()
        }
    }

    private fun setupRecyclerView(view: View) {
        layoutManager = LinearLayoutManager(view.context)
        view.apply {
            adapter.channelClickedListener = this@NewsListFragment
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

            if (navigator.isUserLeftSettingsFragment) {
                showProgress()
                navigator.isUserLeftSettingsFragment = false
            }
        }
    }

    private fun showLoadedNews(it: List<NewsListItems>) {
        adapter.setItems(it)
        fnlNewsRecyclerView.visibility = View.VISIBLE
        fnlProgressBar.visibility = View.GONE
        fnlRefreshLayout.isRefreshing = false
        fnlEmptySourceListTextView.visibility = View.GONE
    }

    override fun onRefresh() {
        viewModel.loadNews()
    }

    private fun showEmptyErrorMessage() {
        fnlEmptySourceListTextView.visibility = View.VISIBLE
        fnlNewsRecyclerView.visibility = View.GONE
        fnlProgressBar.visibility = View.GONE
    }

    override fun onNewClicked(link: String) {
        viewModel.showSelectedNew(link)
    }

    override fun onChannelClicked(category: String) {
        viewModel.setIsChannelSelected(category)
        viewModel.sortAndShowListWithCategory(category)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_LOADED_KEY, viewModel.isLoaded)
    }

    private fun showProgress() {
            fnlNewsRecyclerView.visibility = View.GONE
            fnlProgressBar.visibility = View.VISIBLE
            fnlEmptySourceListTextView.visibility = View.GONE
    }
}