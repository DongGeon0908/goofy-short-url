package com.goofy.shorturl.controller

import com.goofy.shorturl.config.web.APPLICATION_JSON_UTF8_VALUE
import com.goofy.shorturl.service.ShortUrlService
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Short Url API"])
@RestController
@RequestMapping(value = ["/api/v1/short-url"], produces = [APPLICATION_JSON_UTF8_VALUE])
class ShortUrlRestController(
    private val shortUrlService: ShortUrlService
) {
    // TODO : SHORT URL 만들기

    // TODO : SHORT URL 단건 조회

    // TODO : SHORT URL 전체 조회

    // TODO : SHORT URL 수정

    // TODO : SHORT URL 삭제
}
