package com.edernilson.calcorte.backend.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AppConfig {

    @Value("\${cors.originPatterns:default}")
    private val corsOriginPatterns: String = ""

    @Bean
    fun messageSource() = ReloadableResourceBundleMessageSource().apply {
        setBasename("classpath:/i18n/messages")
    }

    @Bean
    fun addCorsConfig(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                val allowedOrigins = corsOriginPatterns.split(",").toTypedArray()
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOriginPatterns(*allowedOrigins)
                    .allowCredentials(true)
            }
        }
    }
}

@Configuration
class OpenAPIConfig {

    @Bean
    fun openAPIDocumentation(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("CALCORTE - Cálculo de Cortes para Marcenarias")
                    .description("API do sistema CALCORTE, rapidez no orçamento de móveis projetados")
                    .version("v1.0")
                    .contact(
                        Contact()
                            .name("Eder Nilson")
                            .email("eder.nilson@gmail.com")
                    )
            )
    }
}