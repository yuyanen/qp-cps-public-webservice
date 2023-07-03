package qp.cps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import qp.cps.security.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.OPTIONS)
		.antMatchers(HttpMethod.GET, "/assets/**")
		.antMatchers(HttpMethod.GET, "/index.html")
		.antMatchers(HttpMethod.GET, "/favicon.ico")
		.antMatchers(HttpMethod.GET, "/*.css")
		.antMatchers(HttpMethod.GET, "/*.*.css")
		.antMatchers(HttpMethod.GET, "/*.gif")
		.antMatchers(HttpMethod.GET, "/*.*.gif")
		.antMatchers(HttpMethod.GET, "/*.js")
		.antMatchers(HttpMethod.GET, "/*.*.js")
		.antMatchers(HttpMethod.GET, "/*.map")
		.antMatchers(HttpMethod.GET, "/*.*.map")
		.antMatchers(HttpMethod.POST, "/api/protected/users/**");
		//.antMatchers(HttpMethod.GET, "/scs/**");	
		}
	
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/pz/protected/**")
//				.allowedMethods("OPTIONS", "GET", "POST")
//				.allowedOrigins(webUrl);
//			}
//		};
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.headers().xssProtection().disable().contentSecurityPolicy("default-src 'self'")
		.and()
		.frameOptions().sameOrigin().addHeaderWriter(new HstsHeaderWriter())
		.and()
		.csrf().disable().authorizeRequests().anyRequest().authenticated()
		.and()
		.addFilterBefore(new JWTAuthenticationFilter("/api/**", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
}