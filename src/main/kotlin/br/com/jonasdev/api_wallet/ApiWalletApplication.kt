package br.com.jonasdev.api_wallet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication
class ApiWalletApplication

fun main(args: Array<String>) {
    runApplication<ApiWalletApplication>(*args)
}
