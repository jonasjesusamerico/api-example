package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.infrastructure.entities.transaction.TransactionEntityFactory
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionRepositoryImp(private val repository: TransactionRepositoryPostgres) : TransactionRepository {

    override fun create(transaction: TransactionDomain) {
        repository.save(TransactionEntityFactory.fromDomain(transaction))
    }

    override fun findById(id: Long): Optional<TransactionDomain> {
        return repository.findById(id)
            .map { t -> TransactionEntityFactory.domainFromEntity(t) }
    }


}