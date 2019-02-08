package com.changr.security;


import javax.servlet.http.HttpServletResponse;

import com.auth0.jwk.JwkProvider;
import com.auth0.spring.security.api.JwtAuthenticationProvider;
import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${auth0.apiAudience}")
    private String apiAudience;
    @Value(value = "${auth0.issuer}")
    private String issuer;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer, this.authenticationProvider())
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").authenticated();
    }

    @Bean
    public JwkProvider jwkProvider()
    {
        return new CognitoJwkProvider(this.issuer);
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        return new JwtAuthenticationProvider(
                this.jwkProvider(),
                this.issuer,
                this.apiAudience
        );
    }
}
