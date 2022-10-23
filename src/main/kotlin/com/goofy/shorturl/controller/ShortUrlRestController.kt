package com.goofy.shorturl.controller

import com.goofy.shorturl.config.web.APPLICATION_JSON_UTF8_VALUE
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Short Url API"])
@RestController
@RequestMapping(value = ["/api/v1/short-url"], produces = [APPLICATION_JSON_UTF8_VALUE])
class ShortUrlRestController {
    // TODO : SHORT URL 만들기
}
