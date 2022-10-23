package com.goofy.shorturl.controller.dto.response

import com.goofy.shorturl.entity.ShortUrl
import com.goofy.shorturl.entity.vo.EncoderType

data class ShortUrlResponse(
    val id: Long,
    val encodedKey: String,
    val url: String,
    val type: EncoderType,
    val description: String?
) {
    constructor(encodedKey: String, shortUrl: ShortUrl) : this(
        id = shortUrl.id,
        encodedKey = encodedKey,
        url = shortUrl.url,
        type = shortUrl.type,
        description = shortUrl.description
    )
}
