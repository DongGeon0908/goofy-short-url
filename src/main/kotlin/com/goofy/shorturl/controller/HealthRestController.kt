package com.goofy.shorturl.controller

import com.goofy.shorturl.controller.dto.HealthResponse
import com.goofy.shorturl.config.web.APPLICATION_JSON_UTF8_VALUE
import com.goofy.shorturl.extension.wrapOk
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/", "health"], produces = [APPLICATION_JSON_UTF8_VALUE])
class HealthRestController(
    private val environment: Environment
) {
    @GetMapping
    fun healthCheck() = HealthResponse(
        profiles = environment.activeProfiles.toList()
    ).wrapOk()
}
