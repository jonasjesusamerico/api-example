package br.com.jonasdev.api_example.infrastructure.entities.customer

import br.com.jonasdev.api_example.domain.model.customer.CustomerType
import br.com.jonasdev.api_example.domain.model.address.Address
import br.com.jonasdev.api_example.domain.model.address.AddressType
import br.com.jonasdev.api_example.domain.model.customer.CustomerDomain
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation

class CustomerEntityFactory {
    companion object {
        fun fromDomain(domain: CustomerDomainRepresentation): CustomerEntity {
            return CustomerEntity(
                id = domain.id,
                name = domain.name,
                companyName = domain.companyName,
                customerType = domain.customerType.name,
                documentRegistration = domain.documentRegistration
            )
        }

        fun updateFromDomain(entity: CustomerEntity, domain: CustomerDomainRepresentation): CustomerEntity {
            entity.id = domain.id
            entity.name = domain.name
            entity.companyName = domain.companyName
            entity.customerType = domain.customerType.name
            entity.documentRegistration = domain.documentRegistration
            return entity
        }

        fun domainFromEntity(entity: CustomerEntity): CustomerDomainRepresentation {
            return CustomerDomain(
                id = entity.id,
                name = entity.name,
                companyName = entity.companyName,
                customerType = entity.customerType.let { CustomerType.valueOf(it) },
                documentRegistration = entity.documentRegistration,
                addresses = setOf(
                    Address(
                        id = 1,
                        street = "Rua 1",
                        number = "123",
                        neighborhood = "Bairro 1",
                        city = "Cidade 1",
                        state = "Estado 1",
                        country = "País 1",
                        zipCode = "12345678",
                        type = AddressType.HOME
                    )
                )
            )
        }
    }
}
