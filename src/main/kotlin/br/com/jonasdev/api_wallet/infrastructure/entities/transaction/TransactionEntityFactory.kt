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
    }
}