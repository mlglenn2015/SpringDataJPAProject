package prv.mark.project.testutils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author mlglenn on 12/12/2016.
 */
@Configuration
@Profile("integration")
public class TestIntegrationConfig extends TestWebServicesConfig {
}
