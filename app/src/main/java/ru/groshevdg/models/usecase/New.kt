package ru.groshevdg.models.usecase

data class New constructor(
    var title: String? = null,
    var description: String? = null,
    var publishDate: String? = null,
    var link: String? = null,
    var category: String? = null
)