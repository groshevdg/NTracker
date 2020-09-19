package ru.groshevdg.ui.news.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_new.view.*
import ru.groshevdg.models.ui.NewsListItems

class NewItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: NewsListItems.NewItem) {
        itemView.apply {
            inCategoryNameTextView.text = item.new.category
            inTitleTextView.text = item.new.title
            inDescriptionTextView.text = item.new.description
            inPublished.text = item.new.publishDate
        }
    }
}