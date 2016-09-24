package com.supernova.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by anup on 9/18/16.
 */
@Configuration
@EnableWebSecurity
public class BeanConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
                .password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("*//**")
                .authorizeRequests()
                .antMatchers("/api/**", "/greeting**", "/webjars*//**")
                .permitAll()
                .anyRequest()
                .authenticated();

        //http.authorizeRequests().antMatchers("/rest/**").authenticated();
        http.csrf().disable();
        //  http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        //  http.formLogin().successHandler(authenticationSuccessHandler);
        //  http.formLogin().failureHandler(authenticationFailureHandler);
    }
}
