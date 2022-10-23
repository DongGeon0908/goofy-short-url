package com.goofy.shorturl.config.async

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableAsync
class AsyncConfig : AsyncConfigurerSupport()
