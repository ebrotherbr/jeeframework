package br.com.ebrother.jeeframework.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe responsável por realizar as configurações de JPA.
 *
 * @author Rafael Braga
 */
@EnableTransactionManagement
@ConfigurationProperties(prefix = "jpa")
public class JPAConfiguration {

    /** Pacotes onde estão as entidades. */
    private String[] packagesToScan;

    /** Driver de conexão ao banco de dados. */
    private String driverClassName;

    /** URL de conexão ao banco de dados. */
    private String url;

    /** Usuario para conexão ao banco de dados. */
    private String username;

    /** Senha do usuário para conexão ao banco de dados. */
    private String password;

    /** Indica se o schema será criado ou validado. */
    private String hibernateHbm2ddlAuto;

    /** Dialeto do banco de dados. */
    private String hibernateDialect;

    /** Indica se o Hibernate irá exibir o SQL gerado nas transações. */
    private String hibernateShowSql;

    /** Indica se o Hibernate irá formatar o SQL gerado nas transações. */
    private String hibernateFormatSql;

    /**
     * Abstrai as funcões do arquivo persistence.xml.
     *
     * @return o objeto {@link LocalContainerEntityManagerFactoryBean}.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.getDataSource());
        em.setPackagesToScan(this.packagesToScan);
        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(this.getAdditionalProperties());
        return em;
    }

    /**
     * Cria o datasource.
     *
     * @return o {@link DataSource}.
     */
    @Bean
    public DataSource getDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    /**
     * Configura as transações para usarem a implementação JPA.
     *
     * @param entityManagerFactory o {@link EntityManagerFactory}.
     * @return {@link PlatformTransactionManager}.
     */
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    /**
     * Cria as propriedades adicionais do Hibernate.
     *
     * @return o arquivo de {@link Properties}.
     */
    private Properties getAdditionalProperties() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", this.hibernateHbm2ddlAuto);
        properties.setProperty("hibernate.dialect", this.hibernateDialect);
        properties.setProperty("hibernate.show_sql", this.hibernateShowSql);
        properties.setProperty("hibernate.format_sql", this.hibernateFormatSql);
        return properties;
    }

    /**
     * @return the packagesToScan
     */
    public String[] getPackagesToScan() {
        return this.packagesToScan;
    }

    /**
     * @param packagesToScan the packagesToScan to set
     */
    public void setPackagesToScan(final String[] packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    /**
     * @return the driverClassName
     */
    public String getDriverClassName() {
        return this.driverClassName;
    }

    /**
     * @param driverClassName the driverClassName to set
     */
    public void setDriverClassName(final String driverClassName) {
        this.driverClassName = driverClassName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the hibernateHbm2ddlAuto
     */
    public String getHibernateHbm2ddlAuto() {
        return this.hibernateHbm2ddlAuto;
    }

    /**
     * @param hibernateHbm2ddlAuto the hibernateHbm2ddlAuto to set
     */
    public void setHibernateHbm2ddlAuto(final String hibernateHbm2ddlAuto) {
        this.hibernateHbm2ddlAuto = hibernateHbm2ddlAuto;
    }

    /**
     * @return the hibernateDialect
     */
    public String getHibernateDialect() {
        return this.hibernateDialect;
    }

    /**
     * @param hibernateDialect the hibernateDialect to set
     */
    public void setHibernateDialect(final String hibernateDialect) {
        this.hibernateDialect = hibernateDialect;
    }

    /**
     * @return the hibernateShowSql
     */
    public String getHibernateShowSql() {
        return this.hibernateShowSql;
    }

    /**
     * @param hibernateShowSql the hibernateShowSql to set
     */
    public void setHibernateShowSql(final String hibernateShowSql) {
        this.hibernateShowSql = hibernateShowSql;
    }

    /**
     * @return the hibernateFormatSql
     */
    public String getHibernateFormatSql() {
        return this.hibernateFormatSql;
    }

    /**
     * @param hibernateFormatSql the hibernateFormatSql to set
     */
    public void setHibernateFormatSql(final String hibernateFormatSql) {
        this.hibernateFormatSql = hibernateFormatSql;
    }

}
