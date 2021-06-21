package ru.geekbrains.SpringwebApp.config;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DBConfiguration {
    @Bean
    public EntityManager entityManager(SessionFactory sessionFactory) {
        return sessionFactory.createEntityManager();

    }
    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("admin");
        dataSource.setURL("jdbc:h2:./PRODUCT");

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("ru.geekbrains.SpringwebApp.model");
        localSessionFactoryBean.setHibernateProperties(properties());
        return localSessionFactoryBean;
    }

    private Properties properties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }
}
