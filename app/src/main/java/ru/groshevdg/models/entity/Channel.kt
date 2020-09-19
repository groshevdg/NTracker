package ru.groshevdg.models.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel")
data class Channel constructor(
    @field:Element(name = "image")
    var image: Image? = null,
    @field:ElementList(name = "item", inline = true)
    var items: List<New>? = null,
    @field:Element(name = "lastBuildDate")
    var buildDate: String? = null,
    @field:Element(name = "link")
    var link: String? = null,
    @field:Element(name = "description")
    var description: String? = null,
    @field:Element(name = "title")
    var title: String? = null
)