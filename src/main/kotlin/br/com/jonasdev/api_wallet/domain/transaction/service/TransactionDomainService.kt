package br.com.jonasdev.api_wallet.domain.transaction.service

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction.TransactionRepository
import java.util.*

class TransactionDomainService(private val repository : TransactionRepository) : TransactionService {

    override fun create(toDomain: TransactionDomain) {
        repository.create(toDomain)
    }

    override fun findById(id: Long): Optional<TransactionDomain> {
        return repository.findById(id)
    }


}