package com.nagarro.bfsitraining.watchstore.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.nagarro.bfsitraining.watchstore.service.impl.UserDetailsServiceImpl;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired 
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public AuthenticationManager authenticationManager(
								AuthenticationConfiguration authConfiguration) 
										throws Exception {
		
	  return authConfiguration.getAuthenticationManager();
	}
	
	/**
	 * Bean to encode the password using BCrypter
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	
	/**
	 * Filtering the url for authentitcation or for public accessibility
	 * @param http
	 * @return 
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.cors(cors -> cors.configurationSource(request ->{
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.setAllowedOrigins(Arrays.asList("*"));
			configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
			configuration.setAllowedHeaders(Arrays.asList("*"));
			return configuration;
		}));
		
		
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorize) -> {
				authorize.requestMatchers("/api/auth/**","/api/user/register").permitAll();
                authorize.requestMatchers(HttpMethod.OPTIONS).permitAll();
                authorize.anyRequest().authenticated();
				
			});
		
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(this.jwtAuthEntryPoint));
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}


}
