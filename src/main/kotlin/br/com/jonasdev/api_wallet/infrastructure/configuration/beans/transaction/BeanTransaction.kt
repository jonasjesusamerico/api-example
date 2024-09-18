package br.com.jonasdev.api_wallet.infrastructure.configuration.beans.transaction

import br.com.jonasdev.api_wallet.domain.repository.transaction.TransactionRepository
import br.com.jonasdev.api_wallet.domain.service.transaction.TransactionDomainService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanTransaction {

    @Bean
    fun transactionService(repository: TransactionRepository) = TransactionDomainService(repository)

}