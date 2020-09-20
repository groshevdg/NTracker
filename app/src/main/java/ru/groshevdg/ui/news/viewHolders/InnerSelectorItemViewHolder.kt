package ru.groshevdg.ui.news.viewHolders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_inner_selector.view.*
import ru.groshevdg.R
import ru.groshevdg.models.ui.InnerSelectorItem

class InnerSelectorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: InnerSelectorItem) {
        itemView.apply {
            iisButton.text = item.selectorName
            setupButtonStyle(item)
        }
    }

    private fun View.setupButtonStyle(item: InnerSelectorItem) {
        if (item.isEnableLoading) {

            if (item.isLoaded) {
                iisButton.setOnClickListener {
                    iisButton.isSelected = !iisButton.isSelected
                }
            } else {
                iisButton.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.failed_state_button)
            }

        } else {
            iisButton.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.disabled_state_button)
        }
    }
}