package net.bulldozer.tourofall.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select user_id,password,enabled from users where username=?")
				.authoritiesByUsernameQuery("select user_id, role from user_roles where user_id=?")
				.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class)
			.authorizeRequests()
				.antMatchers("/recommendation").authenticated()
				.antMatchers("/evalmore").authenticated()
				.antMatchers("/review/write/**").authenticated()
				.antMatchers("/qna/question/write/**").authenticated()
				.antMatchers("/qna/answer/write/**").authenticated()
				.anyRequest().permitAll()
			.and()
			.formLogin();
	}
	
}
