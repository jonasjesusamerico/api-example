package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.infrastructure.entities.transaction.TransactionEntityFactory
import org.springframework.stereotype.Component

@Component
class TransactionRepositoryImp(private val repository: TransactionRepositoryPostgres) : TransactionRepository {

    override fun create(transaction: TransactionDomain) {
        repository.save(TransactionEntityFactory.fromDomain(transaction))
    }


}