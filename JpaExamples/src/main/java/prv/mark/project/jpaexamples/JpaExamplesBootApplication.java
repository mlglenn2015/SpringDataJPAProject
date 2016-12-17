package prv.mark.project.jpaexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * This is the Spring Boot Application class.
 * <p>
 * This code sets up the application context from Spring Boot.
 * </p>
 * @author mlglenn on 12/13/2016.
 */
@SpringBootApplication
//@EnableBatchProcessing
//@EnableScheduling
public class JpaExamplesBootApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaExamplesBootApplication.class);

    /**
     * The main method for the Spring Boot Battery Application.
     *
     * @param args {@link String[]}
     */
    public static void main(String[] args) {

        LOGGER.debug("*** JpaExamplesBootApplication.main() executing ***");

        ApplicationContext ctx = SpringApplication.run(JpaExamplesBootApplication.class, args);
    }

}
