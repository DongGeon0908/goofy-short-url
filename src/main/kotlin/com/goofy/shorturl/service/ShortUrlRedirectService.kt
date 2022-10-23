package com.goofy.shorturl.service

import com.goofy.shorturl.repository.ShortUrlRepository
import org.springframework.stereotype.Service

@Service
class ShortUrlRedirectService(
    private val shortUrlRepository: ShortUrlRepository
) {
}
