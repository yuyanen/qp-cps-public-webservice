package qp.scs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import qp.scs.dto.*;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private ObjectMapper objectMapper = new ObjectMapper();

	public JWTAuthenticationFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		Authentication auth = TokenAuthenticationService.getAuthentication(request, response);

		// means username can be found but no principal (due to server restart while user is still browsing with a valid JWT). so need to refetch.
		if (auth != null && auth.getPrincipal() == null && !auth.getName().isEmpty()) {

			// re-populate authentication with its principal and authorities
			auth = getAuthenticationManager().authenticate(auth);

			// add it back to security context and session map
			TokenAuthenticationService.addAuthentication(request, response, auth);

			// re-try getting the authentication again
			auth = TokenAuthenticationService.getAuthentication(request, response);
		}
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
		SecurityContextHolder.getContext().setAuthentication(auth);
		TokenAuthenticationService.refreshAuthentication(response, auth.getPrincipal(), ((CustomAuthenticationToken) auth).getAuthorities());
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		ErrorDto errorDto = new ErrorDto(HttpStatus.UNAUTHORIZED.value(), failed.getMessage());
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().println(objectMapper.writeValueAsString(errorDto));
	}
}
