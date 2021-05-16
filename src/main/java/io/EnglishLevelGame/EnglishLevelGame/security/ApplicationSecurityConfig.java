package io.EnglishLevelGame.EnglishLevelGame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import io.EnglishLevelGame.EnglishLevelGame.authenticationUser.UserAuthServiceImpl;

import static io.EnglishLevelGame.EnglishLevelGame.security.ApplicationUserRole.*;;
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserAuthServiceImpl userDetailsService;
	
	@Autowired	
	private final PasswordEncoder passwordEncoder;
	
	
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and() 
			.authorizeRequests()
			.antMatchers(HttpMethod.POST).hasAuthority("ROLE_STUDENT")
			.antMatchers(HttpMethod.DELETE).hasRole(STUDENT.name())
			.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
		      .loginPage("/login")
		      .usernameParameter("username")
		      .passwordParameter("password")
		      .loginProcessingUrl("/perform_login")
		      .defaultSuccessUrl("/", true)
		      .failureUrl("/login?error=true")
		    .and()
		    .rememberMe()
		    	.tokenValiditySeconds(2419200)
		    	.key("spittrKeyspittrKeyspittrKeyspittrKeyspittrKeyspittrKeyspittrKey")
		    .and()
		    .logout()
		      .logoutUrl("/logout")
		      .logoutSuccessUrl("/login")
		      .clearAuthentication(true)
		      .deleteCookies("JSESSIONID", "remember-me")
		      .invalidateHttpSession(true)
		      .permitAll();
		      
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		}
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	   @Bean
	    public AuthenticationManager customAuthenticationManager() throws Exception {
	        return authenticationManager();
	    }
	
	

	

}
