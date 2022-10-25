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

    @Transactional
    fun register(request: ShortUrlRequest): ShortUrlResponse {
        val shortUrl = shotUrlRepository.save(
            ShortUrl(
                url = request.url,
                type = request.type,
                description = request.description
            )
        )

        logger.info { "register short url | url : ${request.url}" }

        val encodedKey = microEncoder.encode(shortUrl.id)

        return ShortUrlResponse(encodedKey, shortUrl)
    }

    fun findById(id: Int): ShortUrlResponse {
        val shortUrl = shotUrlRepository.findByIdOrNull(id)
            ?: throw ShortUrlNotFoundException("short url을 찾을 수 없습니다. shortUrlId : $id")

        val encodedKey = microEncoder.encode(shortUrl.id)

        return ShortUrlResponse(encodedKey, shortUrl)
    }

    fun findAll(page: Int, size: Int): Page<ShortUrlResponse> {
        val pageable = PageRequest.of(page, size, Sort.by("createdAt").descending())

        return shotUrlRepository.findAll(pageable)
            .map {
                val encodedKey = microEncoder.encode(it.id)
                ShortUrlResponse(encodedKey, it)
            }
    }

    fun edit(id: Int, request: ShortUrlRequest): ShortUrlResponse {
        val shortUrl = shotUrlRepository.findByIdOrNull(id)
            ?: throw ShortUrlNotFoundException("short url을 찾을 수 없습니다. shortUrlId : $id")

        val changedShortUrl = shotUrlRepository.save(
            shortUrl.apply {
                this.url = request.url
                this.type = request.type
                this.description = request.description
            }
        )

        logger.info { "edit short url | id : $id" }

        val encodedKey = microEncoder.encode(changedShortUrl.id)

        return ShortUrlResponse(encodedKey, changedShortUrl)
    }

    fun delete(id: Int) {
        if (shotUrlRepository.existsById(id)) {
            shotUrlRepository.deleteById(id)
            logger.info { "delete short url | id : $id" }
        }
    }
}
