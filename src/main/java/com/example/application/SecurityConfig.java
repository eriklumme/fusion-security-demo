package com.example.application;

import com.vaadin.flow.spring.VaadinSpringSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set default security policy that permits Vaadin internal requests and
        // denies all other
        VaadinSpringSecurity.configure(http);
        // Ignore the login processing url and vaadin endpoint calls
        http.csrf().ignoringAntMatchers("/login", "/connect/**");
        // specify the URL of the login view, the value of the parameter
        // is the defined route for the login view component.
        http.formLogin().loginPage("/login").permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        // Access to static resources, bypassing Spring security.
        VaadinSpringSecurity.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure users and roles in memory
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN", "USER");
    }
}