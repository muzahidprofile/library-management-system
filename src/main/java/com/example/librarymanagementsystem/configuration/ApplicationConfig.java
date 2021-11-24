package com.example.librarymanagementsystem.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySources({
    @PropertySource("classpath:library.properties")
})
@Order(1)
public class ApplicationConfig {
	@Autowired
	@Qualifier("databaseUserDetailsService")
	private UserDetailsService databaseUserDetailsService;
	
	private BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return passwordEncoder;
    }
	
	@Bean(name = "databaseUserAuthenticationProvider")
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		daoAuthenticationProvider.setUserDetailsService(databaseUserDetailsService);

		return daoAuthenticationProvider;
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
}
