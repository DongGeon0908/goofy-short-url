package com.goofy.shorturl.controller.dto.response

import com.goofy.shorturl.entity.ShortUrl
import com.goofy.shorturl.entity.vo.EncoderType

data class ShortUrlResponse(
    val id: Int,
    val shortenUrl: String,
    val encodedKey: String,
    val originUrl: String,
    val type: EncoderType,
    val description: String?
) {
    constructor(encodedKey: String, shortenUrl: String, shortUrl: ShortUrl) : this(
        id = shortUrl.id,
        shortenUrl = shortenUrl,
        encodedKey = encodedKey,
        originUrl = shortUrl.originUrl,
        type = shortUrl.type,
        description = shortUrl.description
    )
}
