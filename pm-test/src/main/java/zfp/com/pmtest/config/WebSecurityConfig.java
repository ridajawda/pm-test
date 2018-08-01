package zfp.com.pmtest.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import zfp.com.pmtest.security.CustomUserDetailsService;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	        http.formLogin().permitAll()
	            .and()
	            .authorizeRequests()
	            .antMatchers("/").hasAnyRole("USER", "ADMIN")
	            .antMatchers("/employees/**").hasRole("ADMIN")
	            .anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/403");
	    }

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		System.out.println("Password is :" + new BCryptPasswordEncoder().encode("haha"));
		return new BCryptPasswordEncoder();
	}
}