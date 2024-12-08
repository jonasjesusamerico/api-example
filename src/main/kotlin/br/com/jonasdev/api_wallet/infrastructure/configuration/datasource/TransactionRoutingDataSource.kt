package br.com.jonasdev.api_wallet.infrastructure.configuration.datasource

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.support.TransactionSynchronization
import org.springframework.transaction.support.TransactionSynchronizationManager

@Profile("!test")
@Configuration
@EnableJpaRepositories(
    basePackages = ["br.com.jonasdev.api_wallet.infrastructure.repositoy"],
    entityManagerFactoryRef = "entityManager",
    transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
@EntityScan(basePackages = ["br.com.jonasdev.api_wallet.infrastructure.entities"])
class TransactionRoutingDataSource(
    dataBaseProperties: DataBaseProperties,
    hikariPrimaryProperties: HikariPrimaryProperties,
    hikariReplicaProperties: HikariReplicaProperties
): AbstractRoutingDataSource() {
    init {
        val dataSourceMap = HashMap<Any, Any>()
        dataSourceMap[DataSourceType.READ_WRITE] = HikariDataSource(hikariPrimaryProperties.buildConfig(dataBaseProperties))
        dataSourceMap[DataSourceType.READ_ONLY] = HikariDataSource(hikariReplicaProperties.buildConfig(dataBaseProperties))
        this.setTargetDataSources(dataSourceMap)
    }

    override fun determineCurrentLookupKey(): DataSourceType {
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            return DataSourceType.READ_ONLY
        }

        return DataSourceType.READ_WRITE
    }

    @Bean
    @Primary
    fun entityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("br.com.jonasdev.api_wallet.infrastructure")

        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        val jpaDialect = vendorAdapter.jpaDialect
        jpaDialect.setPrepareConnection(false)

        val properties = HashMap<String, Any>()
        properties["hibernate.hbm2ddl.auto"] = "none"
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        em.setJpaPropertyMap(properties)

        return em
    }

    @Bean
    @Primary
    fun dataSource() = LazyConnectionDataSourceProxy(this)
}