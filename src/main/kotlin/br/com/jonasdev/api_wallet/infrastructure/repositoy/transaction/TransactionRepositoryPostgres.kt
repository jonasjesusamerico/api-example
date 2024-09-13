package br.com.jonasdev.api_wallet.infrastructure.repositoy.transaction

import br.com.jonasdev.api_wallet.infrastructure.entities.transaction.TransactionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepositoryPostgres: CrudRepository<TransactionEntity, Long> {
}