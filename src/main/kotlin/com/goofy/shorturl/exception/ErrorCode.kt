package com.goofy.shorturl.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val description: String) {
    /**
     * Internal Server Error Code
     **/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),

    /**
     * Short Url Exception
     **/
    SHORT_URL_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "short url을 찾을 수 없습니다."),
    ;
}
