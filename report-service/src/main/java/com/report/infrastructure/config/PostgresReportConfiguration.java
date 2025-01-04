package com.report.infrastructure.config;

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
        basePackages = {"com.report.infrastructure.repositories"},
        entityManagerFactoryRef = "reportPostgresEntityManager",
        transactionManagerRef = "reportPostgresTransactionManager")
public class PostgresReportConfiguration {
    private Environment env;

    public PostgresReportConfiguration(@Autowired Environment env) {
        this.env = env;
    }


    @Bean(name = "reportPostgresEntityManager")
    @Qualifier("reportDataSource")
    public LocalContainerEntityManagerFactoryBean reportPostgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(reportPostgresDataSource());
        em.setPackagesToScan("com.report.domain.entities");
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

    @Bean(name = "reportDataSource")
    public DataSource reportPostgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.report.postgres.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.report.postgres.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.report.postgres.password"));
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    @Primary
    @Qualifier("report")
    public PlatformTransactionManager reportPostgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(reportPostgresEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = "reportPostgresDataSource")
    @Qualifier("reportDataSource")
    public JdbcTemplate postgresJdbcTemplate() {
        return new JdbcTemplate(reportPostgresDataSource());
    }


}
