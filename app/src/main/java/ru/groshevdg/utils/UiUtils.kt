package ru.groshevdg.utils

import android.content.Context
import android.util.DisplayMetrics

class UiUtils {

    companion object {
        fun dpToPx(context: Context, dp: Int): Int {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            return (dp * (displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT))
        }
    }
}