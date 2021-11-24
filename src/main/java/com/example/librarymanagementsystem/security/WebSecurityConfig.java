package com.example.librarymanagementsystem.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("databaseUserDetailsService")
	private UserDetailsService databaseUserDetailsService;

	@Autowired
	@Qualifier("databaseUserAuthenticationProvider")
	private AuthenticationProvider databaseUserAuthenticationProvider;

	@Autowired
	@Qualifier("externalApiUserAuthenticationProvider")
	private AuthenticationProvider externalApiUserAuthenticationProvider;

	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();

        // Set unauthorized requests exception handler
        http = http
            .exceptionHandling()
            .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getMessage()
                    );
                }
            )
            .and();
        
        
        http.authorizeRequests().antMatchers("/authenticate").permitAll().and();
		http.formLogin().loginPage("/login").and().httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/librarymanagement/api/v1/users/**").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/librarymanagement/api/v1/users/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/librarymanagement/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/librarymanagement/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PATCH, "/librarymanagement/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/librarymanagement/**").hasRole("ADMIN");

		// Add JWT token filter
        http.addFilterBefore(
        		jwtRequestFilter,
            UsernamePasswordAuthenticationFilter.class
        );
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}