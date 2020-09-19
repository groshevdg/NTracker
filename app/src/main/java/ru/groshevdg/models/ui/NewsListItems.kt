package ru.groshevdg.models.ui

import ru.groshevdg.models.usecase.New

sealed class NewsListItems {

    class NewItem(val new: New) : NewsListItems()
    class SelectorItem: NewsListItems()
}