package com.examserver.exam.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.examserver.exam.impl.CustomUserService;
import com.examserver.exam.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private static final String[] WHITE_LIST_URL = 
        {"/Exam/public/**"};
	
	
	 @Autowired
	    private JwtAuthFilter authFilter; 
	
	
	@Bean
	public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception
	{

		
		 http
		 .csrf(AbstractHttpConfigurer::disable)
		 .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
         .authorizeHttpRequests(req -> req
        		 .requestMatchers(WHITE_LIST_URL)
                 .permitAll()
                 
                 .requestMatchers("/Exam/admin/**").hasAnyAuthority("ADMIN")
                 .requestMatchers("/Exam/user/**").hasAnyAuthority("USER")
                 
           	  .anyRequest().authenticated()
           	
           
           	  
           	 
           	  
           	  
         )
         .authenticationProvider(authenticationProvider())
         .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) ;
		
		//http.httpBasic(org.springframework.security.config.Customizer.withDefaults());
		
     return http.build();
	}
	
	
	
	@Bean
	public UserDetailsService userdetailsService()
	{
	
		return new CustomUserService();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		 return new BCryptPasswordEncoder();
	}
	@Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userdetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 

}
