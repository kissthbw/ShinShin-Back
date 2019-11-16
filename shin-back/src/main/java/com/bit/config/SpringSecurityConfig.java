package com.bit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = false)
public class SpringSecurityConfig {

	@Configuration
	@Order(2)
	public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
		
		private static Logger log = LoggerFactory.getLogger(AdminSecurityConfig.class);

		
		@Autowired
		private UserDetailsService userService;
		
		@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		@Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userService);
//	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

		@Autowired
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authenticationProvider());
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			
			log.info( "Configure admin security" );
			
//			http.csrf().disable()
//		      .authorizeRequests().antMatchers("/portal-administrador*", "/portal-administrador/**").hasRole("ADMIN")
//		      .and()
//		      .formLogin()
//		      .loginPage( "/portal-administrador/login" )
//		      .usernameParameter("username")
//		      .passwordParameter("password").permitAll()
//		      .loginProcessingUrl("/portal-administrador/admin_login")
//	          .successForwardUrl("/portal-administrador/postLogin")
//	          .failureUrl("/portal-administrador/login-failed")
//		      .and()
//		      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
			http.antMatcher("/portal-administrador/**" )
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("ADMIN")
	           
	          .and()
	          .formLogin()
	          .loginPage("/portal-administrador/adminLogin")
	          .usernameParameter("username")
	          .passwordParameter("password").permitAll()
	          .loginProcessingUrl("/portal-administrador/admin_login")
	          .successForwardUrl("/portal-administrador/postAdminLogin")
	          .failureUrl("/portal-administrador/adminLogin?error=loginError")
	           
	          .and()
	          .logout()
	          .logoutUrl("/portal-administrador/admin_logout")
	          .logoutSuccessUrl("/portal-administrador/adminLogin")
	          .deleteCookies("JSESSIONID")
	          .invalidateHttpSession(true)
	           
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/portal-administrador/403")
	           
	          .and()
	          .csrf().disable();
		}
	}
	
	@Configuration
	@Order(1)
	public static class UserSecurityConfig extends WebSecurityConfigurerAdapter{
		private static Logger log = LoggerFactory.getLogger(UserSecurityConfig.class);

		
		@Autowired
		private UserDetailsService userService;
		
		@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		@Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userService);
//	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }

		@Autowired
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) {
	        auth.authenticationProvider(authenticationProvider());
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			
			log.info( "Configure user security" );

//			http.csrf().disable()
//		      .authorizeRequests().antMatchers("/portal-usuario*", "/portal-usuario/**").hasRole("USER")
//		      .and()
//		      .formLogin()
//		      .loginPage( "/portal-usuario/login" )
//		      .usernameParameter("username")
//		      .passwordParameter("password").permitAll()
//		      .loginProcessingUrl("/portal-usuario/user_login")
//	          .successForwardUrl("/portal-usuario/postLogin")
//	          .failureUrl("/portal-usuario/login-failed")
//		      .and()
//		      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/portal-usuario/logout"));
			http.antMatcher("/portal-usuario/**")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("USER")
	           
	          .and()
	          .formLogin()
	          .loginPage("/portal-usuario/userLogin")
	          .usernameParameter("username")
	          .passwordParameter("password").permitAll()
	          .loginProcessingUrl("/portal-usuario/user_login")
	          .successForwardUrl("/portal-usuario/postUserLogin")
	          .failureUrl("/portal-usuario/userLogin?error=loginError")
	           
	          .and()
	          .logout()
	          .logoutUrl("/portal-usuario/user_logout")
	          .logoutSuccessUrl("/portal-usuario/adminLogin")
	          .deleteCookies("JSESSIONID")
	          .invalidateHttpSession(true)
	           
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/portal-usuario/403")
	           
	          .and()
	          .csrf().disable();
		}
	}
}

