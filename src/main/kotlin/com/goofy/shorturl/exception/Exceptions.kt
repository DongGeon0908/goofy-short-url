package com.goofy.shorturl.exception

open class BusinessException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.description
) : RuntimeException(message ?: errorCode.description)

/**
 * Short Url Exception
 **/
class ShortUrlNotFoundException(message: String? = null) : BusinessException(
    errorCode = ErrorCode.SHORT_URL_NOT_FOUND_ERROR,
    message = message
)

