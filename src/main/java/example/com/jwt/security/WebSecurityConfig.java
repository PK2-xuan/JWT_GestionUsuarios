package example.com.jwt.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
		
	private final UserDetailsService userDetailsService;
	
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    
    

	public WebSecurityConfig(UserDetailsService userDetailsService, 
			JWTAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, 
			AuthenticationManager authManager) throws Exception{
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		return http
				.cors()
				.and()
				
				.csrf().disable()
				.authorizeHttpRequests()
				
				//.requestMatchers("/api/contactos")
				//.permitAll()
				
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
/*	@Bean 
	UserDetailsService userDetailsService(){
		InMemoryUserDetailsManager manager = new  InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin")
			   .password(passwordEncoder().encode("admin"))
			   .roles()
			   .build());
		return manager;
	}*/
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		}
	/*
	 //sirve para que la contraseña salga incriptada ( la contraseña en el ejemplo es xuan13)
	public static void main(String[] args) {
		System.out.println("pass: " + new BCryptPasswordEncoder().encode("xuan13"));
	}*/
}










