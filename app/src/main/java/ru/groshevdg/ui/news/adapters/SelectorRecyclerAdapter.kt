package ru.groshevdg.ui.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.groshevdg.R
import ru.groshevdg.models.ui.InnerSelectorItem
import ru.groshevdg.ui.news.viewHolders.InnerSelectorItemViewHolder

class SelectorRecyclerAdapter : RecyclerView.Adapter<InnerSelectorItemViewHolder>() {
    private val itemsList = mutableListOf<InnerSelectorItem>()

    fun setItems(items: List<InnerSelectorItem>) {
        itemsList.clear()
        itemsList.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerSelectorItemViewHolder {
        return InnerSelectorItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inner_selector, parent, false))
    }

    override fun onBindViewHolder(holder: InnerSelectorItemViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}