package prv.mark.project.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import prv.mark.project.common.service.impl.ApplicationMessageSource;
import prv.mark.project.common.service.impl.ApplicationParameterSource;
import prv.mark.project.testutils.config.TestDataConfig;

/**
 * Spring Configuration for unit tests.
 *
 * @author mlglenn
 */
@Configuration
@Import({TestDataConfig.class})
@ComponentScan(basePackages = {"prv.mark.project"})
@PropertySource("classpath:/common-test.properties")
@Profile("test")
public class TestCommonConfig {

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.setValidationMessageSource(messageSource());
        return validatorFactory;
    }

    @Bean
    public MessageSource messageSource() {
        return new ApplicationMessageSource();
    }

    @Bean
    public ApplicationParameterSource applicationParameterSource() {
        return new ApplicationParameterSource();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("common-test.properties"));
        return configurer;
    }

}
