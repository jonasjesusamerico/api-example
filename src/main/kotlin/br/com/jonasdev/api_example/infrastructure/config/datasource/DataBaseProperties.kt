package br.com.jonasdev.api_example.infrastructure.config.datasource

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource")
data class DataBaseProperties (
    val url: String,
    val urlReplica: String,
    val username: String,
    val password: String,
    val driverClassName: String
)