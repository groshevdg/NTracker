package ru.groshevdg.models.entity

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssNews constructor(
    @field:Attribute(name = "version")
    var version: String? = null,
    @field:Element(name = "channel")
    var channel: Channel? = null
)