package ru.groshevdg.models.ui

sealed class NewsListItems {

    class NewItem(val new: ru.groshevdg.models.usecase.NewItem.New) : NewsListItems()
    class SelectorItem(val itemsList: List<InnerSelectorItem>): NewsListItems()
}