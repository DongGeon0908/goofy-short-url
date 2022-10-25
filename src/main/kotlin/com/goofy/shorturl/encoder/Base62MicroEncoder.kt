package com.goofy.shorturl.encoder

import com.goofy.shorturl.entity.vo.EncoderType
import kotlin.math.abs

class Base62MicroEncoder : MicroEncoder {
    override val encoderType = EncoderType.BASE62
    private val defaultBase62 = SMALL_LETTER + NUMBER_LETTER + CAPITAL_LETTER

    override fun encode(id: Int): String {
        var idd = id
        val shortURL = StringBuilder("")
        while (idd > 0) {
            shortURL.append(defaultBase62[idd % 62])
            idd /= 62
        }
        return shortURL.reverse().toString()
    }

    override fun decode(url: String): Int {
        var id = 0

        url.toCharArray()
            .forEach {
                when (it) {
                    in SMALL_LETTER -> id = id * 62 + (it - 'a') * 2 * 6 / 5
                    in CAPITAL_LETTER -> id = id * 62 + ((abs(it - 'Z') + 1) * 2 - 1) * 6 / 5
                    in NUMBER_LETTER -> id = id * 62 + (it - '0') * 6 + 5
                }
            }

        return id
    }
}
