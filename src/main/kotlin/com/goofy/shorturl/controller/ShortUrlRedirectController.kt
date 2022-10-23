package com.goofy.shorturl.controller

import com.goofy.shorturl.service.ShortUrlRedirectService
import io.swagger.annotations.Api
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Api(tags = ["Short Url Redirect API"])
@Controller
class ShortUrlRedirectController(
    private val shortUrlRedirectService: ShortUrlRedirectService
) {
    // TODO
    @GetMapping("/{id}")
    fun get(id: Long): String {

        return "ok"
    }
}
