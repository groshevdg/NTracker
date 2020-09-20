package ru.groshevdg.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news_list.*
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import ru.groshevdg.R
import ru.groshevdg.di.components.DaggerFragmentComponent
import ru.groshevdg.di.factory.ViewModelFactory
import ru.groshevdg.misc.ItemSpaceDecorator
import ru.groshevdg.ui.ApplicationActivity
import ru.groshevdg.ui.news.adapters.NewsListRecyclerAdapter
import javax.inject.Inject

class NewsListFragment : Fragment() {
    private val adapter = NewsListRecyclerAdapter()
    private lateinit var layoutManager: LinearLayoutManager
    @Inject lateinit var factory: ViewModelFactory
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

        if (adapter.itemCount == 0) {
            viewModel.loadNews()
            view.fnlProgressBar.visibility = View.VISIBLE
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.newsLiveData.observe(viewLifecycleOwner, {
            adapter.setItems(it)
            fnlProgressBar.visibility = View.GONE
        })
    }
}