package br.com.jonasdev.api_wallet.infrastructure.configuration.datasource

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource")
data class DataBaseProperties(
    val url: String,
    val username: String,
    val password: String,
    val urlReplica: String,
    val usernameReplica: String,
    val passwordReplica: String,
    val driverClassName: String
)