package br.com.jonasdev.api_wallet.infrastructure.entities.transaction

import br.com.jonasdev.api_wallet.domain.representation.transaction.TransactionEntityRepresentation
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "transaction")
class TransactionEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,

    override var purchaseDate: LocalDate = LocalDate.MIN,

    override var description: String = "",

    override var sector: String = "",

    override var paymentMethod: String = "",

    override var amount: BigDecimal = BigDecimal.ZERO,

    override var status: Boolean = false

) : TransactionEntityRepresentation

