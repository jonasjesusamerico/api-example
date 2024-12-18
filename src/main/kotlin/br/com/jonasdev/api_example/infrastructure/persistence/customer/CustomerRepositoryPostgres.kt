package br.com.jonasdev.api_example.infrastructure.persistence.customer

import br.com.jonasdev.api_example.infrastructure.entities.customer.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepositoryPostgres : JpaRepository<CustomerEntity, Long>