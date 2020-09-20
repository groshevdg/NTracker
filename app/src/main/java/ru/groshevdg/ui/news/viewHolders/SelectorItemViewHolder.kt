package ru.groshevdg.ui.news.viewHolders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_selector.view.*
import ru.groshevdg.misc.ItemSpaceDecorator
import ru.groshevdg.models.ui.NewsListItems
import ru.groshevdg.ui.news.adapters.SelectorRecyclerAdapter

class SelectorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val adapter = SelectorRecyclerAdapter()
    private var firstTime = true

    fun bind(item: NewsListItems.SelectorItem) {
        itemView.apply {
            isSelectorRecyclerView.adapter = adapter
            isSelectorRecyclerView.layoutManager = LinearLayoutManager(this.context,
                LinearLayoutManager.HORIZONTAL, false)
            adapter.setItems(item.itemsList)

            if (firstTime) {
                isSelectorRecyclerView.addItemDecoration(ItemSpaceDecorator(
                    marginTopInDp = 0,
                    marginRightInDp = 5,
                    marginBottomInDp = 0,
                    marginLeftInDp = 5
                ))
                firstTime = false
            }
        }
    }
}