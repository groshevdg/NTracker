package ru.groshevdg.ui.news.viewHolders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_inner_selector.view.*
import ru.groshevdg.R
import ru.groshevdg.models.ui.InnerSelectorItem
import ru.groshevdg.utils.ListsUtils

class InnerSelectorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: InnerSelectorItem, clickedListener: OnChannelClickedListener) {
        itemView.apply {
            iisButton.text = itemView.context.getString(ListsUtils.sourceNames[item.selectorName]!!)
            iisButton.isSelected = item.isSelected
            setupButtonStyle(item, clickedListener)
        }
    }

    private fun View.setupButtonStyle(item: InnerSelectorItem, clickedListener: OnChannelClickedListener) {
        if (item.isLoaded) {
            iisButton.setOnClickListener {
                iisButton.isSelected = !iisButton.isSelected
                item.isSelected = !item.isSelected
                clickedListener.onChannelClicked(iisButton.text.toString())
            }
        } else {
            iisButton.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.failed_state_button)
        }
    }
}