package prv.mark.project.testutils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring Data Test Configuration.
 *
 * https://github.com/olivergierke/repositories-deepdive/blob/master/src/main/java/de/olivergierke/deepdive/ApplicationConfig.java
 *
 * @author mlglenn on 12/12/2016.
 */
@Configuration
@ComponentScan(basePackages = {"prv.mark.project"})
@EnableJpaRepositories(basePackages = {"prv.mark.project.common.repository"})
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:/TEST.properties")
})
@Profile({"test"})
public class TestDataConfig {

    @Value("${application.jpa.logging:SEVERE}")
    private String jpaLogging;

    @Value("${application.jpa.show-sql}")
    private String showSql;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName("STOCKS")
                .addScript("classpath:TEST-schema.sql")
                .addScript("classpath:TEST-data.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());
        return template;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();

        emfb.setDataSource(dataSource());
        emfb.setPackagesToScan("prv.mark.project.common.entity");
        emfb.setJpaDialect(new EclipseLinkJpaDialect());

        //emfb.setPersistenceXmlLocation();

        AbstractJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabase(Database.HSQL);

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("eclipselink.weaving", "false");
        jpaProperties.setProperty("eclipselink.logging.level", "ALL");

        emfb.setJpaProperties(jpaProperties);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.afterPropertiesSet();

        return emfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
