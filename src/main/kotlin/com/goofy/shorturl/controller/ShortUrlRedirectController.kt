package com.goofy.shorturl.controller

import com.goofy.shorturl.service.ShortUrlRedirectService
import io.swagger.annotations.Api
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Api(tags = ["Short Url Redirect API"])
@Controller("/shorten")
class ShortUrlRedirectController(
    private val shortUrlRedirectService: ShortUrlRedirectService
) {
    @GetMapping("/{key}")
    fun get(@PathVariable key: String): String {
        val redirectUrl = shortUrlRedirectService.getShortUrl(key)
        return "redirect:$redirectUrl"
    }
}
