package ru.groshevdg.ui.news.viewHolders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_inner_selector.view.*
import ru.groshevdg.R
import ru.groshevdg.models.ui.InnerSelectorItem
import ru.groshevdg.utils.ListsUtils

class InnerSelectorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: InnerSelectorItem) {
        itemView.apply {
            iisButton.text = itemView.context.getString(ListsUtils.sourceNames[item.selectorName]!!)
            setupButtonStyle(item)
        }
    }

    private fun View.setupButtonStyle(item: InnerSelectorItem) {
        if (item.isLoaded) {
            iisButton.setOnClickListener {
                iisButton.isSelected = !iisButton.isSelected
            }
        } else {
            iisButton.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.failed_state_button)
        }
    }
}