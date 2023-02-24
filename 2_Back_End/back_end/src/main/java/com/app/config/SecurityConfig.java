package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.app.filter.JwtFilter;
import com.app.service.UserDetailServiceImpl;

@EnableWebSecurity // mandatory
@Configuration // mandatory
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private JwtFilter JwtFilter;
	@Autowired
	private UserDetailServiceImpl useImpl;

	public SecurityConfig() {
		System.out.println("\n------- CTOR: SECURITY CONFIG -------\n");
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticatonMgr(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/user/login", "/swagger-ui.html", "/user/register").permitAll().anyRequest().authenticated()
				.and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(daoAuthenticationProvider());
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(useImpl);
		provider.setPasswordEncoder(encoder());
		return provider;
	}
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { System.out.
	 * println("\n----------- SECURITY CONFIG - configure AuthenticationManagerBuilder --------------\n"
	 * ); // Tell authentication manager to look for credentials with the following
	 * // service // Use the userDetailsServiceImpl for custom user POJO
	 * auth.userDetailsService(useImpl); }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() {
	 * System.out.println("\n----------- Building PasswordEncoder --------------\n"
	 * ); return NoOpPasswordEncoder.getInstance(); // -- deprecated // return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * @Bean
	 * 
	 * @Override public AuthenticationManager authenticationManagerBean() throws
	 * Exception { // Return the authentication manager System.out.
	 * println("\n---------- Building AuthenticationManager bean -------------\n");
	 * return super.authenticationManagerBean(); }
	 * 
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * System.out.
	 * println("\n------- IN SECURITY CONFIG configure HttpSecurity -------\n");
	 * 
	 * http.cors().and().csrf().disable().authorizeRequests().antMatchers(
	 * "/user/login", "/user/register").permitAll()
	 * .anyRequest().authenticated().and().exceptionHandling().and().
	 * sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * http.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
	 * }
	 */
}
