package br.com.jonasdev.api_wallet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class ApiWalletApplication

fun main(args: Array<String>) {
    runApplication<ApiWalletApplication>(*args)
}
