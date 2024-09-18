package br.com.jonasdev.api_wallet.infrastructure.entities.transaction

import br.com.jonasdev.api_wallet.domain.model.transaction.TransactionDomain
import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionDomainRepresentation

class TransactionEntityFactory {
    companion object {
        fun fromDomain(domain: TransactionDomainRepresentation): TransactionEntity {
            return TransactionEntity(
                id = domain.id,
                purchaseDate = domain.purchaseDate,
                description = domain.description,
                sector = domain.sector,
                paymentMethod = domain.paymentMethod,
                amount = domain.amount,
                status = domain.status,

                )
        }

        fun updateFromDomain(entity: TransactionEntity, domain: TransactionDomainRepresentation): TransactionEntity {
            entity.purchaseDate = domain.purchaseDate
            entity.description = domain.description
            entity.sector = domain.sector
            entity.paymentMethod = domain.paymentMethod
            entity.amount = domain.amount
            entity.status = domain.status
            return entity
        }

        fun domainFromEntity(entity: TransactionEntity): TransactionDomainRepresentation {
            return TransactionDomain(
                id = entity.id,
                purchaseDate = entity.purchaseDate,
                description = entity.description,
                sector = entity.sector,
                paymentMethod = entity.paymentMethod,
                amount = entity.amount,
                status = entity.status,
            )
        }
    }
}