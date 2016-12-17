package prv.mark.project.testutils.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Spring Test Configuration.
 *
 * @author mlglenn on 12/12/2016.
 */
@Configuration
@ComponentScan(basePackages = {"prv.mark.project"})
@PropertySource("classpath:TEST.properties")
@Profile("test")
public class TestConfig {

    @Autowired
    private Environment environment;

    /*@Value("${application.key.store.password}")
    private String keyStorePassword;
    @Value("${application.trust.store.password}")
    private String trustStorePassword;*/


}
