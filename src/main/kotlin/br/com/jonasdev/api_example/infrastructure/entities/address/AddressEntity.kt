package br.com.jonasdev.api_example.infrastructure.entities.address

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "address")
data class AddressEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "street")
    val street: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "complement")
    val complement: String? = null,

    @Column(name = "neighborhood")
    val neighborhood: String,

    @Column(name = "city")
    val city: String,

    @Column(name = "state")
    val state: String,

    @Column(name = "country")
    val country: String,

    @Column(name = "zip_code")
    val zipCode: String,

    @Column(name = "type")
    val type: String
)