package br.com.jonasdev.api_wallet.infrastructure.entities.transaction

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain

class TransactionEntityFactory {
    companion object {
        fun fromDomain(domain: TransactionDomain): TransactionEntity {
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

        fun domainFromEntity(entity: TransactionEntity) : TransactionDomain {
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