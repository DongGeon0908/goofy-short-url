package com.goofy.shorturl.exception

import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = KotlinLogging.logger { }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<ErrorResponseDto> {
        logger.error { "BusinessException ${e.message}" }
        val response = ErrorResponseDto(e.errorCode, e)
        return ResponseEntity(response, e.errorCode.status)
    }
}
