package ru.groshevdg.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.groshevdg.App
import ru.groshevdg.R
import ru.groshevdg.data.repository.NewsRepository
import ru.groshevdg.misc.ItemSpaceDecorator
import ru.groshevdg.models.ui.InnerSelectorItem
import ru.groshevdg.models.ui.NewsListItems 
import ru.groshevdg.ui.news.adapters.NewsListRecyclerAdapter
import ru.groshevdg.usecase.NewsUseCase

class NewsListFragment : Fragment() {
    private val adapter = NewsListRecyclerAdapter()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        layoutManager = LinearLayoutManager(view.context)
        view.apply {
            fnlNewsRecyclerView.adapter = adapter
            fnlNewsRecyclerView.layoutManager = layoutManager
            fnlNewsRecyclerView.addItemDecoration(ItemSpaceDecorator(
                marginTopInDp = 8,
                marginRightInDp = 0,
                marginBottomInDp = 16,
                marginLeftInDp = 0))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            val news = NewsUseCase(NewsRepository(App.instance.apiService)).getNews()
            val items = mutableListOf<NewsListItems>()
            items.add(NewsListItems.SelectorItem(mutableListOf(InnerSelectorItem("auto", true, true),
                InnerSelectorItem("theatre", true, true), InnerSelectorItem("hot_news", true, true))))
            news.map { items.add(NewsListItems.NewItem(it)) }
            view.post { adapter.setItems(items) }
        }
    }
}