package ru.groshevdg.models.usecase

sealed class NewItem {

    data class New constructor(
        var title: String? = null,
        var description: String? = null,
        var publishDate: String? = null,
        var link: String? = null,
        var category: String? = null) : NewItem()

    class Channel constructor(
        var title: String,
        var isChannelLoaded: Boolean) : NewItem()
}

