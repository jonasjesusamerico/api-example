package br.com.jonasdev.api_wallet.infrastructure.entities.transaction

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "transaction")
class TransactionEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    val purchaseDate: LocalDate,

    val description: String,

    val sector: String,

    val paymentMethod: String,

    val amount: BigDecimal,

    var status: Boolean
) {

}
