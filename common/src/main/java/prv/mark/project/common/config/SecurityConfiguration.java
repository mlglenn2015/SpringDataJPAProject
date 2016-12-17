package prv.mark.project.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration for manual restart URLs.
 *
 * @author mlglenn.
 */
//@Configuration
//@EnableWebSecurity
//@Profile({"local", "dev", "qatest", "staging", "production"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.user.name}")
    private String springUser;

    @Value("${spring.user.password}")
    private String springPasswd;


    /**
     * Sets the user authorization to configured values in the application.properties file.
     *
     * @param auth {@link AuthenticationManagerBuilder}
     * @throws Exception
     */
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser(springUser).password(springPasswd).roles("USER");
    }*/

}
