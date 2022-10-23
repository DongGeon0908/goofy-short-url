package com.goofy.shorturl.config.database

import com.zaxxer.hikari.HikariDataSource
import org.hibernate.cfg.AvailableSettings
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.orm.hibernate5.SpringBeanContainer
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

// TODO : Master Slave Setup 진행
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.goofy.shorturl.repository"],
    entityManagerFactoryRef = "goofyEntityManager",
    transactionManagerRef = "goofyTransactionManager"
)
class GoofyDatabaseConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "goofy.master.datasource")
    fun goofyMasterDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "goofy.master.datasource.hikari")
    fun goofyMasterHikariDataSource(
        @Qualifier("goofyMasterDataSourceProperties") masterProperty: DataSourceProperties
    ): HikariDataSource {
        return masterProperty
            .initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun goofyNamedParameterJdbcTemplate(
        @Qualifier("goofyMasterHikariDataSource") dataSource: DataSource,
    ): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "goofy.jpa")
    fun goofyJpaProperties(): JpaProperties {
        return JpaProperties()
    }

    @Bean
    @Primary
    fun goofyEntityManager(
        entityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        configurableListableBeanFactory: ConfigurableListableBeanFactory,
        @Qualifier("goofyMasterHikariDataSource") goofyDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val properties = mapOf(
            AvailableSettings.BEAN_CONTAINER to SpringBeanContainer(configurableListableBeanFactory)
        )

        return entityManagerFactoryBuilder
            .dataSource(goofyDataSource)
            .packages("com.goofy.shorturl.entity")
            .properties(properties)
            .build()
    }

    @Bean
    @Primary
    fun goofyTransactionManager(
        @Qualifier("goofyEntityManager") goofyEntityManager: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(goofyEntityManager)
    }
}
