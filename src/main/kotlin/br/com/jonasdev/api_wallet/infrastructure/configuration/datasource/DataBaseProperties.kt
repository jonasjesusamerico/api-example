package br.com.jonasdev.api_wallet.infrastructure.configuration.datasource

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "spring.datasource")
data class DataBaseProperties (
    val url: String,
    val urlReplica: String,
    val username: String,
    val password: String,
    val driverClassName: String
)