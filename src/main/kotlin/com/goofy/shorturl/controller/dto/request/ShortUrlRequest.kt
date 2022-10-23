package com.goofy.shorturl.controller.dto.request

import com.goofy.shorturl.entity.vo.EncoderType

data class ShortUrlRequest(
    val url: String,
    val type: EncoderType,
    val description: String?
)
