package com.goofy.shorturl.config.encoder

import com.goofy.shorturl.encoder.Base62MicroEncoder
import com.goofy.shorturl.encoder.MicroEncoder
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EncoderConfig {
    @Bean
    @ConditionalOnMissingBean(name = ["base62Encoder"])
    fun base62Encoder(): MicroEncoder {
        return Base62MicroEncoder()
    }
}
