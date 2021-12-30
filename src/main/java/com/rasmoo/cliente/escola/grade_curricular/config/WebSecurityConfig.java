package com.rasmoo.cliente.escola.grade_curricular.config;

import com.rasmoo.cliente.escola.grade_curricular.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configuracaoGlobal(AuthenticationManagerBuilder auth, UserInfoService userInfo) throws Exception {

        auth.userDetailsService(userInfo).passwordEncoder(this.encoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] allowed = new String[] {
                "/webjars", "/api/usuarios", "static/**"
        };

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(allowed).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}