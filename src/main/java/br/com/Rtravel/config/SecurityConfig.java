package br.com.Rtravel.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.Rtravel.security.JWTAuthenticationFilter;
import br.com.Rtravel.security.JWTAuthorizationFilter;
import br.com.Rtravel.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends  WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHES = { //Arraay dos endpoint que vão ser permitidos os acessos
			"/h2-console/**",
			"/property/**",
			"/property/1/**",
			"/**",
			
	};
	
	private static final String[] PUBLIC_SWAGGER_TEST= {
			"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = { //dpoint array that a non-logged in user can only retrieve data from
			"/user/**",
			"/property/**"
			
			//Aqui pode colocar os endpoints separados por virgula 
	};
	private static final String[] PUBLIC_MATCHERS_POST = { //Endpoint array that a non-logged in user can only add data to	
			"/user/**",
			"/auth/forgot/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_PUT = { //Endpoint array that a non-logged in user can only add data to	
			"/user/**",
	};
	
	private static final String[] PUBLIC_MATCHERS_DELETE = { //Endpoint array that a non-logged in user can only add data to	
			"/user/**",
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
	
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
			http.authorizeRequests()
			.antMatchers(HttpMethod.GET,PUBLIC_SWAGGER_TEST).permitAll();
		}
    
		http.cors().and().csrf().disable();  //desativa função que protege contra ataque de armazenamento de em seção
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_PUT).permitAll()
			.antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll()
			.antMatchers(PUBLIC_MATCHES).permitAll()
			.anyRequest().authenticated();  //Request authentication for remaining endpoints
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() { //Allows access to endpoints with basic settings
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	}
	
	@Bean
	public  BCryptPasswordEncoder  bCryptPasswordEncoder() { //Causes password when saving password to encrypted database
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("DELETE");
	    config.addAllowedMethod("PATCH");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
}
