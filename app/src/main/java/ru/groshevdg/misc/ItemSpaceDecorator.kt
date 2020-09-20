package ru.groshevdg.misc

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.groshevdg.utils.UiUtils

class ItemSpaceDecorator(private var marginTopInDp: Int,
                         private var marginRightInDp: Int,
                         private var marginBottomInDp: Int,
                         private var marginLeftInDp: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = UiUtils.dpToPx(parent.context, marginTopInDp)
        outRect.right = UiUtils.dpToPx(parent.context, marginRightInDp)
        outRect.bottom = UiUtils.dpToPx(parent.context, marginBottomInDp)
        outRect.left = UiUtils.dpToPx(parent.context, marginLeftInDp)
    }
}