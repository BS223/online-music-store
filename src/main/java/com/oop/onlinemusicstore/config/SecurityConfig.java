package com.oop.onlinemusicstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/css/**","/font/**","/img/**","/js/**", "/scss/**","/startboot/**","/user","/file/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
//                .loginPage("/login_page")
                .defaultSuccessUrl("/main")
                .and().logout().logoutSuccessUrl("/");

        http.csrf().disable();           //TODO enable csrf
    }
}