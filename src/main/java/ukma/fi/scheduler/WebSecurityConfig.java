package ukma.fi.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * Encoder of password
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Method builder for security configuration
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select login, password, 'true' from user " +
                                "where login=?")
                .authoritiesByUsernameQuery(
                        "select login, status from user " +
                                "where login=?");
    }

    /**
     * Method for security configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .logout()
                .and()
                .authorizeRequests()
                .antMatchers("/student/**").hasAuthority("STUDENT")
                .antMatchers("/teacher/**").hasAuthority("TEACHER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/profile").hasAnyAuthority("TEACHER", "STUDENT")
                .antMatchers("/**", "/h2-console/**").permitAll()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and().exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .formLogin()
                .defaultSuccessUrl("/success_login")
                .failureUrl("/fail_login")
                .permitAll();
    }

}
