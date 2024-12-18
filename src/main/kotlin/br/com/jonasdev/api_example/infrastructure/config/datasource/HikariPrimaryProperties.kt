package br.com.jonasdev.api_example.infrastructure.config.datasource

import com.zaxxer.hikari.HikariConfig
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource.hikari.primary")
data class HikariPrimaryProperties(
    val maxLifetime: Long,
    val maximumPoolSize: Int,
    val minimumIdle: Int,
    val idleTimeout: Long,
    val poolName: String,
    val connectionTimeout: Long,
    val leakDetectionThreshold: Long,
    val validationTimeout: Long,
) {
    fun buildConfig(dataBaseProperties: DataBaseProperties) = this.let {
        HikariConfig().apply {
            this.maximumPoolSize = it.maximumPoolSize
            this.connectionTimeout = it.connectionTimeout
            this.minimumIdle = it.minimumIdle
            this.idleTimeout = it.idleTimeout
            this.maxLifetime = it.maxLifetime
            this.leakDetectionThreshold = it.leakDetectionThreshold
            this.validationTimeout = it.validationTimeout

            dataBaseProperties.let {
                this.jdbcUrl = it.url
                this.username = it.username
                this.password = it.password
                this.driverClassName = it.driverClassName
            }
        }
    }
}