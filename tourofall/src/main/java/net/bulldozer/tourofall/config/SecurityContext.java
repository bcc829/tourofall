package net.bulldozer.tourofall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import net.bulldozer.tourofall.security.service.AuthenticationUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService())
		.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class).authorizeRequests().antMatchers("/recommendation")
				.authenticated().antMatchers("/evalmore").authenticated().antMatchers("/review/write/**")
				.authenticated().antMatchers("/qna/question/write/**").authenticated()
				.antMatchers("/qna/answer/write/**").authenticated().antMatchers("/myinfo/**").authenticated()
				.anyRequest().permitAll().and().formLogin();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder("53cr3t");
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new AuthenticationUserDetailsService();
	}
}
