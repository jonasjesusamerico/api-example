package br.com.jonasdev.api_example.infrastructure.entities.customer

import br.com.jonasdev.api_example.infrastructure.entities.address.AddressEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
data class CustomerEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "company_name")
    var companyName: String?,

    @Column(name = "customer_type")
    var customerType: String,

    @Column(name = "document_registration")
    var documentRegistration: String,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "customer_address",
        joinColumns = [JoinColumn(name = "customer_id")],  // Chave estrangeira para CustomerEntity
        inverseJoinColumns = [JoinColumn(name = "address_id")] // Chave estrangeira para Address
    )
    val addresses: Set<AddressEntity> = emptySet()

)

