package com.greatlearning.studentManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.studentManagementSystem.service.impl.UserDetailsServiceImpl;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(requests -> requests
				.antMatchers("/", "/Student/new", "/Student/add", "/Student/403").hasAnyAuthority("USER", "ADMIN")
				.antMatchers("/Student/update/{id}", "/Student/delete/{id}").hasAuthority("ADMIN")
				.anyRequest().authenticated())
				.formLogin(login -> login.loginProcessingUrl("/login").defaultSuccessUrl("/Student/list").permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
				.exceptionHandling(handling -> handling.accessDeniedPage("/Student/403"))
				.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable());
	}

}
