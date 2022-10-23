package com.goofy.shorturl.config.web

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

const val APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8"

@Configuration
class WebConfig : WebMvcConfigurer {
    @Bean
    @ConditionalOnMissingBean(name = ["corsFilter"])
    fun corsFilter(): CorsFilter {
        val config = CorsConfiguration()
            .apply {
                this.allowCredentials = true
                this.addAllowedOrigin(CorsConfiguration.ALL)
                this.addAllowedHeader(CorsConfiguration.ALL)
                this.addAllowedMethod(CorsConfiguration.ALL)
                this.maxAge = 3600
            }

        val source = UrlBasedCorsConfigurationSource()
            .apply {
                this.registerCorsConfiguration("/**", config)
            }

        return CorsFilter(source)
    }
}
