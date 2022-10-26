package com.goofy.shorturl.service

import com.goofy.shorturl.controller.dto.request.ShortUrlRequest
import com.goofy.shorturl.controller.dto.response.ShortUrlResponse
import com.goofy.shorturl.encoder.MicroEncoder
import com.goofy.shorturl.entity.ShortUrl
import com.goofy.shorturl.exception.ShortUrlNotFoundException
import com.goofy.shorturl.repository.ShortUrlRepository
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ShortUrlService(
    private val shotUrlRepository: ShortUrlRepository,
    private val microEncoder: MicroEncoder
) {
    private val logger = KotlinLogging.logger {}
    private val PREFIX = "localhost:8080/shorten"

    @Transactional
    fun register(request: ShortUrlRequest): ShortUrlResponse {
        val shortUrl = shotUrlRepository.save(
            ShortUrl(
                originUrl = request.url,
                type = request.type,
                description = request.description
            )
        )

        logger.info { "register origin url | url : ${request.url}" }

        val encodedKey = microEncoder.encode(shortUrl.id)

        val shortenUrl = PREFIX + encodedKey

        return ShortUrlResponse(encodedKey, shortenUrl, shortUrl)
    }

    fun findById(id: Int): ShortUrlResponse {
        val shortUrl = shotUrlRepository.findByIdOrNull(id)
            ?: throw ShortUrlNotFoundException("short url을 찾을 수 없습니다. shortUrlId : $id")

        val encodedKey = microEncoder.encode(shortUrl.id)

        val shortenUrl = PREFIX + encodedKey

        return ShortUrlResponse(encodedKey, shortenUrl, shortUrl)
    }

    fun findAll(page: Int, size: Int): Page<ShortUrlResponse> {
        val pageable = PageRequest.of(page, size, Sort.by("createdAt").descending())

        return shotUrlRepository.findAll(pageable)
            .map {
                val encodedKey = microEncoder.encode(it.id)
                val shortenUrl = PREFIX + encodedKey
                ShortUrlResponse(encodedKey, shortenUrl, it)
            }
    }

    fun edit(id: Int, request: ShortUrlRequest): ShortUrlResponse {
        val shortUrl = shotUrlRepository.findByIdOrNull(id)
            ?: throw ShortUrlNotFoundException("short url을 찾을 수 없습니다. shortUrlId : $id")

        val changedShortUrl = shotUrlRepository.save(
            shortUrl.apply {
                this.originUrl = request.url
                this.type = request.type
                this.description = request.description
            }
        )

        logger.info { "edit short url | id : $id" }

        val encodedKey = microEncoder.encode(changedShortUrl.id)
        val shortenUrl = PREFIX + encodedKey

        return ShortUrlResponse(encodedKey, shortenUrl, changedShortUrl)
    }

    fun delete(id: Int) {
        if (shotUrlRepository.existsById(id)) {
            shotUrlRepository.deleteById(id)
            logger.info { "delete short url | id : $id" }
        }
    }
}
