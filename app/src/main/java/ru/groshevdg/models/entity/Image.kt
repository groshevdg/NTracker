package ru.groshevdg.models.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image")
class Image constructor(
    @field:Element(name = "link")
    var link: String? = null,
    @field:Element(name = "title")
    var title: String? = null,
    @field:Element(name = "url")
    var url: String? = null
)