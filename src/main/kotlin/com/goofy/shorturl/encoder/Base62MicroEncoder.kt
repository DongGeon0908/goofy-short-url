package com.goofy.shorturl.encoder

import com.goofy.shorturl.entity.vo.EncoderType

class Base62MicroEncoder : MicroEncoder {
    override val encoderType = EncoderType.BASE62
    private val defaultBase62 = SMALL_LETTER + NUMBER_LETTER + CAPITAL_LETTER

    override fun encode(id: Int): String {
        var key = id
        var shortUrl = ""

        while (key > 0) {
            shortUrl += defaultBase62[key % 62]
            key /= 62
        }

        return shortUrl
    }

    override fun decode(url: String): Int {
        var id = 0

        url.toCharArray().forEach {
            when (it) {
                in SMALL_LETTER -> id = decodeOperateSmallLetter(id, it.code)
                in CAPITAL_LETTER -> id = decodeOperateCapitalLetter(id, it.code)
                in NUMBER_LETTER -> id = decodeOperateNumberLetter(id, it.code)
            }
        }

        return id
    }

    private fun decodeOperateNumberLetter(id: Int, urlPiece: Int): Int {
        return id * 62 + urlPiece - '0'.code + 26
    }

    private fun decodeOperateCapitalLetter(id: Int, urlPiece: Int): Int {
        return id * 62 + urlPiece - 'A'.code + 36
    }

    private fun decodeOperateSmallLetter(id: Int, urlPiece: Int): Int {
        return id * 62 + urlPiece - 'a'.code
    }
}
