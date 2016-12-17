package prv.mark.project.jpaexamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;
//import org.springframework.boot.context.web.SpringBootServletInitializer; //1.2.5.RELEASE
//import org.springframework.boot.web.support.SpringBootServletInitializer; //1.4.2.RELEASE

/**
 * TODO
 * Created by mlglenn on 12/13/2016.
 */
public class JpaExamplesSpringBootInitializer extends SpringBootServletInitializer
        implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaExamplesSpringBootInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        LOGGER.debug("*** JpaExamplesSpringBootInitializer.configure() ***");

        return application.sources(JpaExamplesBootApplication.class);
    }

}
