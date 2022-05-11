package lt.ku.studentai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.ku.studentai.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		
		auth
			.userDetailsService(this.userService)
			.passwordEncoder(bc);
		
		/*
		auth
			.inMemoryAuthentication()
				.withUser("gediminas").password(bc.encode("LabasRytas")).roles("admin", "user")
				.and()
				.withUser("jonas").password(bc.encode("LabasVakaras")).roles("user");
		*/		
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/student/new").hasAnyRole("admin")
				.antMatchers("/api/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/student/").permitAll()
				.antMatchers("/group/").permitAll()
				.antMatchers("/login*").permitAll()
				.antMatchers("/register*").permitAll()
				.anyRequest().authenticated()
		
		.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login-error")
		.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
		.and()
			.csrf()
				.disable();
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
