package com.itran.fgoc.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
//        jwtAuthenticationFilter.setAuthenticationSuccessHandler();
        return new JWTAuthenticationFilter();
    }

    /**
     * 认证管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
//        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationManagerBean());

        http.cors()
                .and()
             .csrf()
                .disable()
             .authorizeRequests()
                .antMatchers("/doc.html", "/webjars/**", "/v2/**", "/swagger-resources", "/generate.html").permitAll()
                .anyRequest().authenticated()
                .and()
            //添加自定义Filter
            .addFilter(jwtAuthenticationFilter())
//            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;


    }
}
