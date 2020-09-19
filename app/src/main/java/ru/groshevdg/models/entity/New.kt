package ru.groshevdg.models.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item")
class New constructor(
    @field:Element(name = "link")
    var link: String? = null,
    @field:Element(name = "guid")
    var guid: String? = null,
    @field:Element(name = "description")
    var description: String? = null,
    @field:Element(name = "title")
    var title: String? = null,
    @field:Element(name = "pubDate")
    var publishDate: String? = null
)