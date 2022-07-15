package ch.zli.m223.ksh19s.mw.CRM.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ch.zli.m223.ksh19s.mw.CRM.roles.AppRoles;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	UserDetailsService userDetailsService;
	BCryptPasswordEncoder bcriptPasswordEncoder;

	public WebSecurityConfiguration(UserDetailsService userDetailsService,
			BCryptPasswordEncoder bcriptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bcriptPasswordEncoder = bcriptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcriptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		configureWeb(http);
		configureRest(http);
	}

	private void configureWeb(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers("/").permitAll().antMatchers("/admin/**").hasAuthority(AppRoles.ADMIN)
				.antMatchers("/deleteUser/**").hasAuthority(AppRoles.ADMIN).antMatchers("/user/**")
				.hasAnyAuthority(AppRoles.USER).antMatchers("/logedin").authenticated().and().formLogin().permitAll() // loginpage
																														// zugänglich
																														// für
																														// jeden
				.and().logout().permitAll(); // logoutpage zugänglich für jeden
	}

	private void configureRest(HttpSecurity http) {
	}

}
