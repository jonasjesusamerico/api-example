package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.domain.repository.transaction.TransactionRepository
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import br.com.jonasdev.api_wallet.infrastructure.entities.transaction.TransactionEntityFactory
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionRepositoryImp(private val repository: TransactionRepositoryPostgres) : TransactionRepository {

    override fun create(transaction: TransactionDomainRepresentation) {
        repository.save(TransactionEntityFactory.fromDomain(transaction))
    }

    override fun findById(id: Long): Optional<TransactionDomainRepresentation> {
        return repository.findById(id)
            .map { t -> TransactionEntityFactory.domainFromEntity(t) }
    }

    override fun findAll(): List<TransactionDomainRepresentation> {
        return repository.findAll().map { t -> TransactionEntityFactory.domainFromEntity(t) }
    }

    override fun update(id: Long, transaction: TransactionDomainRepresentation) {
        val entity = repository.findById(id)
        if (entity.isPresent.not()) {
            throw EntityNotFoundException("Transaction with id $id not found.")
        }

        repository.save(TransactionEntityFactory.updateFromDomain(entity.get(), transaction))
    }

    override fun delete(id: Long) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return
        }

        throw EntityNotFoundException("Transaction with id $id does not exist.")
    }


}