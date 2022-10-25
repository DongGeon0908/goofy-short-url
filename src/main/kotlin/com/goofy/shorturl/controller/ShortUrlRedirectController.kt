package com.goofy.shorturl.controller

import com.goofy.shorturl.service.ShortUrlRedirectService
import io.swagger.annotations.Api
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Api(tags = ["Short Url Redirect API"])
@Controller
class ShortUrlRedirectController(
    private val shortUrlRedirectService: ShortUrlRedirectService
) {
    @GetMapping("/{url}")
    fun get(@PathVariable url: String) = "redirect:${shortUrlRedirectService.getShortUrl(url)}"
}
