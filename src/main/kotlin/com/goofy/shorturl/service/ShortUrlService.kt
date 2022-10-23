package com.goofy.shorturl.service

import com.goofy.shorturl.controller.dto.request.ShortUrlRegisterRequest
import com.goofy.shorturl.controller.dto.response.ShortUrlResponse
import com.goofy.shorturl.encoder.Base62UrlEncoder
import com.goofy.shorturl.entity.ShortUrl
import com.goofy.shorturl.repository.ShortUrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShortUrlService(
    private val shotUrlRepository: ShortUrlRepository,
    private val base62UrlEncoder: Base62UrlEncoder
) {
    @Transactional
    fun register(request: ShortUrlRegisterRequest): ShortUrlResponse {
        val shortUrl = shotUrlRepository.save(
            ShortUrl(
                url = request.url,
                type = request.type,
                description = request.description
            )
        )

        val encodedKey = base62UrlEncoder.encode(shortUrl.url)

        return ShortUrlResponse(encodedKey, shortUrl)
    }
}
