package com.goofy.shorturl.controller.dto

import java.time.LocalDateTime

data class HealthResponse(
    var profiles: List<String>,
    var health: String = "Health Good",
    var time: LocalDateTime = LocalDateTime.now()
)
