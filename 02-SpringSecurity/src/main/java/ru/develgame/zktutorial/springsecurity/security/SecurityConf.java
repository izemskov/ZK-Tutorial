/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService detailsService;

    private static final String ZUL_FILES = "/zkau/web/**/*.zul";
    private static final String[] ZK_RESOURCES = {
            "/zkau/web/**/js/**",
            "/zkau/web/**/zul/css/**",
            "/zkau/web/**/font/**",
            "/zkau/web/**/img/**"
    };
    // allow desktop cleanup after logout or when reloading login page
    private static final String REMOVE_DESKTOP_REGEX = "/zkau\\?dtid=.*&cmd_0=rmDesktop&.*";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ZK already sends a AJAX request with a built-in CSRF token,
        // please refer to https://www.zkoss.org/wiki/ZK%20Developer's%20Reference/Security%20Tips/Cross-site%20Request%20Forgery
        http.csrf().disable();

        http.authorizeRequests()
                // block direct access to zul files
                .antMatchers(ZUL_FILES).denyAll()
                // allow zk resources
                .antMatchers(HttpMethod.GET, ZK_RESOURCES).permitAll()
                // allow desktop cleanup
                .regexMatchers(HttpMethod.GET, REMOVE_DESKTOP_REGEX).permitAll()
                // allow desktop cleanup from ZATS
                .requestMatchers(req -> "rmDesktop".equals(req.getParameter("cmd_0"))).permitAll()
                // accept any requests to login and logout pages
                .mvcMatchers("/login","/logout").permitAll()
                // any requests to other pages have to have authorization
                .anyRequest().hasAnyAuthority("ADMIN")
                // login page
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/hello")
                // logout page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService);
        auth.authenticationProvider(authenticationProvider());
    }
}
