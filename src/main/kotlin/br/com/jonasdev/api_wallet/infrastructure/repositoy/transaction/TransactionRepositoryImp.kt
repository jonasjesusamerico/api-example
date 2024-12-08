package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.domain.configuration.pageable.InternPageable
import br.com.jonasdev.api_wallet.domain.repository.transaction.TransactionRepository
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation
import br.com.jonasdev.api_wallet.infrastructure.entities.transaction.TransactionEntityFactory
import br.com.jonasdev.api_wallet.library.pageable.InternPageableImpl
import br.com.jonasdev.api_wallet.library.pageable.PageableConverter
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class TransactionRepositoryImp(private val repository: TransactionRepositoryPostgres) : TransactionRepository {

    override fun create(transaction: TransactionDomainRepresentation) {
        repository.save(TransactionEntityFactory.fromDomain(transaction))
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Optional<TransactionDomainRepresentation> {
        return repository.findById(id)
            .map { t -> TransactionEntityFactory.domainFromEntity(t) }
    }

    override fun findAll(pageable: InternPageable<TransactionDomainRepresentation>): InternPageableImpl<TransactionDomainRepresentation> {

        val page = repository.findAll(PageableConverter.convert(pageable))
        val pageDomain = page.map { TransactionEntityFactory.domainFromEntity(it) }.toList()
        return InternPageableImpl(pageDomain, page.number, page.size, page.totalElements, page.sort.toString())
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
