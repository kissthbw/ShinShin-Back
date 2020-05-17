package com.bit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bit.config.security.SimpleAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = false)
public class SpringSecurityConfig {
	@Configuration
	@Order(7)
	public static class EmpresaSecurityConfig extends WebSecurityConfigurerAdapter{

		private static Logger log = LoggerFactory.getLogger(UserSecurityConfig.class);

		
		@Autowired
		private UserDetailsService userService;
		
		@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		public SimpleAuthenticationFilter authenticationFilter() throws Exception {
	        SimpleAuthenticationFilter filter = new SimpleAuthenticationFilter();
	        filter.setAuthenticationManager(authenticationManagerBean());
	        filter.setAuthenticationFailureHandler(failureHandler());
	        filter.setFilterProcessesUrl( "/portal-empresa/login" );
	        filter.setAuthenticationSuccessHandler(successHandler());
	        return filter;
	    }
		
		public SimpleUrlAuthenticationFailureHandler failureHandler() {
	        return new SimpleUrlAuthenticationFailureHandler("/portal-empresa/empresaLogin?error=loginError");
	    }
		
		public ForwardAuthenticationSuccessHandler successHandler() {
			return new ForwardAuthenticationSuccessHandler("/portal-empresa/postEmpresaLogin");
	    }
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authProvider());
	    }

	    public AuthenticationProvider authProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userService);
	        return provider;
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			
			log.info( "Configure user security" );
			http
			.antMatcher("/portal-empresa/**")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("ADMIN")
	          .and()
	          .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	          .formLogin()
	          .loginPage("/portal-empresa/empresaLogin")
	          .usernameParameter("username")
	          .passwordParameter("password").permitAll()
	          .loginProcessingUrl("/portal-empresa/login")
	          .successForwardUrl("/portal-empresa/postEmpresaLogin")
	          .failureUrl("/portal-empresa/empresaLogin?error=loginError")
	           
	          .and()
	          .logout()
	          .logoutUrl("/portal-empresa/empresa_logout")
	          .logoutSuccessUrl("/portal-empresa/empresaLogin")
	          .deleteCookies("JSESSIONID")
	          .invalidateHttpSession(true)
	           
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/portal-empresa/403")
	           
	          .and()
	          .csrf().disable();
		}
	
	}
	
	@Configuration
	@Order(6)
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
	@Order(5)
	public static class UserSecurityConfig extends WebSecurityConfigurerAdapter{
		private static Logger log = LoggerFactory.getLogger(UserSecurityConfig.class);

		
		@Autowired
		private UserDetailsService userService;
		
		@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		public SimpleAuthenticationFilter authenticationFilter() throws Exception {
	        SimpleAuthenticationFilter filter = new SimpleAuthenticationFilter();
	        filter.setAuthenticationManager(authenticationManagerBean());
	        filter.setAuthenticationFailureHandler(failureHandler());
	        filter.setFilterProcessesUrl( "/portal-usuario/login" );
	        filter.setAuthenticationSuccessHandler(successHandler());
	        return filter;
	    }
		
		public SimpleUrlAuthenticationFailureHandler failureHandler() {
	        return new SimpleUrlAuthenticationFailureHandler("/portal-usuario/userLogin?error=loginError");
	    }
		
		public ForwardAuthenticationSuccessHandler successHandler() {
			return new ForwardAuthenticationSuccessHandler("/portal-usuario/postUserLogin");
//	        return new SimpleUrlAuthenticationSuccessHandler("/portal-usuario/postUserLogin");
	    }
		
		@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authProvider());
	    }

	    public AuthenticationProvider authProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userService);
	        return provider;
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			
			log.info( "Configure user security" );
			http
			.antMatcher("/portal-usuario/**")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("USER")
	          .and()
	          .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	          .formLogin()
	          .loginPage("/portal-usuario/userLogin")
	          .usernameParameter("username")
	          .passwordParameter("password").permitAll()
	          .loginProcessingUrl("/portal-usuario/login")
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
	
	@Configuration
	@Order(4)
	public static class UserRecuperarSecurityConfig extends WebSecurityConfigurerAdapter{
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
			
			log.info( "UserRecuperarSecurityConfig" );

			http.antMatcher("/portal-usuario/restaurar/**")
	          .authorizeRequests()
	          .anyRequest()
	          .permitAll()	           
	          .and()
	          .csrf().disable();
		}
	}
	
	@Configuration
	@Order(3)
	public static class AdminRecuperarSecurityConfig extends WebSecurityConfigurerAdapter{
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
			
			log.info( "AdminRecuperarSecurityConfig" );

			http.antMatcher("/portal-administrador/restaurar/**")
	          .authorizeRequests()
	          .anyRequest()
	          .permitAll()	           
	          .and()
	          .csrf().disable();
		}
	}
	
	@Configuration
	@Order(2)
	public static class EmpresaRecuperarSecurityConfig extends WebSecurityConfigurerAdapter{
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
			
			log.info( "EmpresaRecuperarSecurityConfig" );

			http.antMatcher("/portal-empresa/restaurar/**")
	          .authorizeRequests()
	          .anyRequest()
	          .permitAll()	           
	          .and()
	          .csrf().disable();
		}
	}
	@Configuration
	@Order(1)
	public static class ReporteProblemaSecurityConfig extends WebSecurityConfigurerAdapter{
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
			
			log.info( "ReporteProblemaSecurityConfig" );

			http.antMatcher("/portal-administrador/envia-problema")
	          .authorizeRequests()
	          .anyRequest()
	          .permitAll()	           
	          .and()
	          .csrf().disable();
		}
	}
}

