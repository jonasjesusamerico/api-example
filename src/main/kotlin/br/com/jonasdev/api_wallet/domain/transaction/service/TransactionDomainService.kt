package br.com.jonasdev.api_wallet.domain.transaction.service

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction.TransactionRepository

class TransactionDomainService(private val repository : TransactionRepository) : TransactionService {

    override fun create(toDomain: TransactionDomain) {
        repository.create(toDomain)
    }


}