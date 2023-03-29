package com.rs.retailstore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

//	@Bean
//	public UserDetailsService userDetailsService (DataSource dataSource) {
//		UserDetails user = User.builder()
//				.username("c6userv1")
//				.password("$2a$10$CDjt1hhvGSh72rn2ZwVKWuKX0K0KiyEwf1ZjvrjcIxNFjc0Gp1mu.")
//				.roles("USER")
//				.build();
//		
//		UserDetails admin = User.builder()
//				.username("c6adminv1")
//				.password("$2a$10$CDjt1hhvGSh72rn2ZwVKWuKX0K0KiyEwf1ZjvrjcIxNFjc0Gp1mu.")
//				.roles("USER", "ADMIN")
//				.build();
//		
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.createUser(user);
//		users.createUser(admin);
//		
//		return users;
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
			.authorizeRequests()
				.antMatchers("/v1/register").permitAll()
				.antMatchers("/v1/greeting").authenticated()
			.and().formLogin()
			.and().httpBasic();			
		
		return httpSecurity.build();
	}
	

}
