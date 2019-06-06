package com.boraji.tutorial.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	  private UserDetailsService userDetailsService;
	  
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	  
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  }
	  
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
    .csrf().disable()
    .authorizeRequests()
    .anyRequest()
    .hasAnyRole("ADMIN", "USER")
    .and()
    .formLogin()
	.loginPage("/login").permitAll().failureUrl("/error")
	.and()
	.rememberMe()  
    .key("rem-me-key")  
    .rememberMeParameter("remember") // it is name of checkbox at login page  
    .rememberMeCookieName("rememberlogin") // it is name of the cookie  
    .tokenValiditySeconds(1000) // remember for number of seconds  
    .and()  
	.logout().invalidateHttpSession(true)
	.clearAuthentication(true)
	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	.logoutSuccessUrl("/logout").logoutUrl("/views/logout.jsp").permitAll(); // Authenticate users with HTTP basic authentication
  }
 /* protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll();
		
	}*/
  public static void main(String[] args) {
     String encoded=new BCryptPasswordEncoder().encode("admin@123");
     System.out.println(encoded);
  }
}
