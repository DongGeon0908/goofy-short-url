package com.goofy.shorturl.service

import com.goofy.shorturl.encoder.MicroEncoder
import com.goofy.shorturl.exception.ShortUrlNotFoundException
import com.goofy.shorturl.repository.ShortUrlRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ShortUrlRedirectService(
    private val shortUrlRepository: ShortUrlRepository,
    private val microEncoder: MicroEncoder
) {
    fun getShortUrl(key: String): String {
        val id = microEncoder.decode(key)

        val shortUrl = shortUrlRepository.findByIdOrNull(id)
            ?: throw ShortUrlNotFoundException()

        return shortUrl.originUrl
    }
}
