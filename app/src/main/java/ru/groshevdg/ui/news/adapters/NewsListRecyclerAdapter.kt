package ru.groshevdg.ui.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.groshevdg.R
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.ui.news.viewHolders.NewItemViewHolder
import ru.groshevdg.ui.news.viewHolders.SelectorItemViewHolder

class NewsListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemsList = mutableListOf<NewsListItems>()

    companion object {
        const val SELECTOR_ITEM = 0
        const val NEW_ITEM = 1
    }

    fun setItems(items: List<NewsListItems>) {
        itemsList.clear()
        itemsList.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == SELECTOR_ITEM) {
            SelectorItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_selector, parent, false)
            )
        } else NewItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_new, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (itemsList[position] is NewsListItems.SelectorItem) {
            (holder as SelectorItemViewHolder).bind(itemsList[position] as NewsListItems.SelectorItem)
        } else {
            (holder as NewItemViewHolder).bind(itemsList[position] as NewsListItems.NewItem)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsList[position] is NewsListItems.SelectorItem) SELECTOR_ITEM
        else NEW_ITEM
    }
}
