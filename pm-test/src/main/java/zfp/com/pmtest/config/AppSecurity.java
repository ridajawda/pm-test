package zfp.com.pmtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	UserBuilder users = User.withDefaultPasswordEncoder();
	auth.inMemoryAuthentication()
	.withUser(users.username("jon").password("test").roles("employee"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		       .anyRequest().authenticated()
		       .and()
		       .formLogin()
		       .loginPage("/showLogin")
		       .loginProcessingUrl("/authenticateTheUser")
		       .permitAll();
	}

	
}
