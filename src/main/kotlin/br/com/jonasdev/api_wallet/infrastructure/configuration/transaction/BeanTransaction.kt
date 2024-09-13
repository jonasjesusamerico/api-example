package br.com.jonasdev.api_wallet.infrastructure.configuration.transaction

import br.com.jonasdev.api_wallet.domain.transaction.service.TransactionDomainService
import br.com.jonasdev.api_wallet.domain.transaction.service.TransactionService
import br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction.TransactionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanTransaction {

    @Bean
    fun transactionService(repository: TransactionRepository) = TransactionDomainService(repository)

}