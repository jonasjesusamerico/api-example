package br.com.jonasdev.api_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
@EntityScan("br.com.jonasdev.api_example.infrastructure.entities")
class ApiExampleApplication

fun main(args: Array<String>) {
    runApplication<ApiExampleApplication>(*args)
}
