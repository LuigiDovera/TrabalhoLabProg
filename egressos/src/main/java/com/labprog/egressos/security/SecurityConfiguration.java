package com.labprog.egressos.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.labprog.egressos.service.EgressoService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private EgressoService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, SecurityConstants.EGRESSO_POR_ID_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.EGRESSOS_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.CURSO_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.FAIXA_SALARIO_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.GARGO_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.DEPOIMENTO_URL).permitAll()
                // a linha a seguir pode ser retirada
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
                // URL pública
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // quem vai autenticar e como
                .addFilter(new AuthenticationFilter(authenticationManager()))
                // quem vai autorizar e como
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // configura o método de autenticação
        auth.userDetailsService(service)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTION"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source
                .registerCorsConfiguration("/**", configuration
                        .applyPermitDefaultValues());
        return source;
    }

}