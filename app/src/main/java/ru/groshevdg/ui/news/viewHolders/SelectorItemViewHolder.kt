package ru.groshevdg.ui.news.viewHolders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_selector.view.*
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.ui.news.adapters.SelectorRecyclerAdapter

class SelectorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val adapter = SelectorRecyclerAdapter()

    fun bind(item: NewsListItems.SelectorItem) {
        itemView.apply {
            isSelectorRecyclerView.adapter = adapter
            isSelectorRecyclerView.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL, false)
            adapter.setItems(item.itemsList)
        }
    }
}