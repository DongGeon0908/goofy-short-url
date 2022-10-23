package com.goofy.shorturl.controller

import com.goofy.shorturl.config.web.APPLICATION_JSON_UTF8_VALUE
import com.goofy.shorturl.controller.dto.request.ShortUrlRequest
import com.goofy.shorturl.extension.wrapOk
import com.goofy.shorturl.extension.wrapPage
import com.goofy.shorturl.extension.wrapVoid
import com.goofy.shorturl.service.ShortUrlService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Short Url API"])
@RestController
@RequestMapping(value = ["/api/v1/short-url"], produces = [APPLICATION_JSON_UTF8_VALUE])
class ShortUrlRestController(
    private val shortUrlService: ShortUrlService
) {
    @ApiOperation("Short Url 등록 API")
    @PostMapping
    fun register(
        @RequestBody request: ShortUrlRequest
    ) = shortUrlService.register(request).wrapOk()

    @ApiOperation("Short Url 단건 조회")
    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ) = shortUrlService.findById(id).wrapOk()

    @ApiOperation("Short Url 전체 조회")
    @GetMapping
    fun getAll(
        @RequestParam(defaultValue = "10") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ) = shortUrlService.findAll(page, size).wrapPage()

    @ApiOperation("Short Url 수정 API")
    @PatchMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @RequestBody request: ShortUrlRequest
    ) = shortUrlService.edit(id, request).wrapOk()


    @ApiOperation("Short Url 삭제 API")
    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ) = shortUrlService.delete(id).wrapVoid()
}
