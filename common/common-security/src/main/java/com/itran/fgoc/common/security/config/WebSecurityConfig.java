package com.itran.fgoc.common.security.config;

import cn.hutool.json.JSONUtil;
import com.itran.fgoc.common.core.api.Response;
import com.itran.fgoc.common.core.api.ResultCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtTokenFilter jwtTokenFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域,禁用csrf
        http = http.cors().and().csrf().disable();

        // 关闭 session
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // 配置认证请求
        http = http.authorizeRequests()
                // 不用认证的请求
                .antMatchers("/login").permitAll()
                .antMatchers("/webjars/**", "/swagger-resources/**", "/v2/**", "/doc.html").permitAll()
                // 需要认证的请求
                .anyRequest().authenticated()
                .and();

        // 未认证的异常请求处理
        http = http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.setHeader("Content-Type", "application/json;charset=UTF-8");
                            response.getWriter().println(JSONUtil.toJsonStr(Response.failed(ResultCode.UNAUTHORIZED)));
                        }
                ).and();

        // 添加 jwt 过滤器, 在认证之前执行
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
