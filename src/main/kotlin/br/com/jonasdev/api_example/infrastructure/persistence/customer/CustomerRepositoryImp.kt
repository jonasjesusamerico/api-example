package br.com.jonasdev.api_example.infrastructure.persistence.customer

import br.com.jonasdev.api_example.domain.configuration.pageable.InternPageable
import br.com.jonasdev.api_example.domain.repository.customer.CustomerRepository
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation
import br.com.jonasdev.api_example.infrastructure.entities.customer.CustomerEntityFactory
import br.com.jonasdev.api_example.library.pageable.InternPageableImpl
import br.com.jonasdev.api_example.library.pageable.PageableConverter
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class CustomerRepositoryImp(private val repository: CustomerRepositoryPostgres) : CustomerRepository {

    override fun create(transaction: CustomerDomainRepresentation) {
        repository.save(CustomerEntityFactory.fromDomain(transaction))
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Optional<CustomerDomainRepresentation> {
        return repository.findById(id)
            .map { t -> CustomerEntityFactory.domainFromEntity(t) }
    }

    override fun findAll(pageable: InternPageable<CustomerDomainRepresentation>): InternPageableImpl<CustomerDomainRepresentation> {

        val page = repository.findAll(PageableConverter.convert(pageable))
        val pageDomain = page.map { CustomerEntityFactory.domainFromEntity(it) }.toList()
        return InternPageableImpl(pageDomain, page.number, page.size, page.totalElements, page.sort.toString())
    }

    override fun update(id: Long, transaction: CustomerDomainRepresentation) {
        val entity = repository.findById(id)
        if (entity.isPresent.not()) {
            throw EntityNotFoundException("Transaction with id $id not found.")
        }

        repository.save(CustomerEntityFactory.updateFromDomain(entity.get(), transaction))
    }

    override fun delete(id: Long) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            return
        }

        throw EntityNotFoundException("Transaction with id $id does not exist.")
    }
}
