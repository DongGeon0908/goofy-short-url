package com.goofy.shorturl.encoder

import com.goofy.shorturl.entity.vo.EncoderType

class Base62UrlEncoder : UrlEncoder {
    override val encoderType = EncoderType.BASE62
    private val base62 = SMALL_LETTER + NUMBER_LETTER + CAPITAL_LETTER

    override fun encode(url: String): String {
        TODO("Not yet implemented")
    }

    override fun decode(url: String): String {
        TODO("Not yet implemented")
    }
}
