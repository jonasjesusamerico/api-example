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

    @Column(name = "purchase_date")
    override var purchaseDate: LocalDate = LocalDate.MIN,

    @Column(name = "description")
    override var description: String = "",

    @Column(name = "sector")
    override var sector: String = "",

    @Column(name = "payment_method")
    override var paymentMethod: String = "",

    @Column(name = "amount")
    override var amount: BigDecimal = BigDecimal.ZERO,

    @Column(name = "status")
    override var status: Boolean = false

) : TransactionEntityRepresentation

