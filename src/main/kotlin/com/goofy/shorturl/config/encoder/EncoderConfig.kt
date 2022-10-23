package com.goofy.shorturl.config.encoder

import com.goofy.shorturl.encoder.Base62MicroEncoder
import com.goofy.shorturl.encoder.MicroEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EncoderConfig {
    @Bean
    fun base62Encoder(): MicroEncoder {
        return Base62MicroEncoder()
    }
}
