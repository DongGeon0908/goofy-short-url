package com.goofy.shorturl.encoder

import com.goofy.shorturl.entity.vo.EncoderType

interface MicroEncoder {
    val encoderType: EncoderType

    fun encode(id: Int): String

    fun decode(url: String): Int
}

val SMALL_LETTER = 'a'..'z'
val CAPITAL_LETTER = 'A'..'Z'
val NUMBER_LETTER = '0'..'9'
