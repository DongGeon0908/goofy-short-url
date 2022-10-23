package com.goofy.shorturl.service

import com.goofy.shorturl.repository.ShortUrlRepository
import org.springframework.stereotype.Service

@Service
class ShortUrlService(
    private val shotUrlRepository: ShortUrlRepository
) {

}
