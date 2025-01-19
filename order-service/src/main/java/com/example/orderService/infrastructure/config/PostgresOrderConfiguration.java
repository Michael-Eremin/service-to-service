package com.example.orderService.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource({"classpath:application.yml"})
@EnableJpaRepositories(
        basePackages = {"com.example.orderService.infrastructure.repositories"},
        entityManagerFactoryRef = "orderPostgresEntityManager",
        transactionManagerRef = "orderPostgresTransactionManager")
public class PostgresOrderConfiguration {
    private Environment env;

    public PostgresOrderConfiguration(@Autowired Environment env) {
        this.env = env;
    }


    @Bean(name = "orderPostgresEntityManager")
    @Qualifier("orderDataSource")
    public LocalContainerEntityManagerFactoryBean orderPostgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(orderPostgresDataSource());
        em.setPackagesToScan("com.example.orderService.domain.entities");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.jdbc.batch_size", 1000);
        properties.put("hibernate.generate_statistics", false);
        properties.put("hibernate.jdbc.fetch_size", 100);
        properties.put("hibernate.order_inserts", true);
        properties.put("hibernate.order_updates", true);
//        properties.put("hibernate.show_sql", true);
        // For using "pool-lo" optimiser for generating ID when using JPA @SequenceGenerator
        properties.put("hibernate.id.optimizer.pooled.preferred", "pooled-lo");
//        properties.put("hibernate.dialect", "com.qusolve.backend.config.datasource.PGDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "orderDataSource")
    public DataSource orderPostgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.order.postgres.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.order.postgres.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.order.postgres.password"));
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    @Primary
    @Qualifier("order")
    public PlatformTransactionManager orderPostgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(orderPostgresEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "orderPostgresDataSource")
    @Qualifier("orderDataSource")
    public JdbcTemplate postgresJdbcTemplate() {
        return new JdbcTemplate(orderPostgresDataSource());
    }


}
