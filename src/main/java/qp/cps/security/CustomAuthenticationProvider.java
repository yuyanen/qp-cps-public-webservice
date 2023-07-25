package qp.cps.security;

import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import qp.cps.service.UserService;
import qp.cps.model.*;

@Component
@Transactional
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	UserService userService;

	public CustomAuthenticationProvider() {
		logger.info("*** CustomAuthenticationProvider created");
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String message = "";

		if (authentication.getName().equals("")) {
			throw new AuthenticationCredentialsNotFoundException("You are not authorised to access this resource.");
		}

		// unique identifier for user is loginId 
		User user = userService.getUser(authentication.getName());
		if (user == null) {
			message = "Invalid login ID.";

			throw new UsernameNotFoundException(message);
		}

		

		String selectedRoleCode = null;
		Set<GrantedAuthority> authorities = Sets.newHashSet();

	
		message = "User and selected role verified.";

		logger.info(message);
		// logger.info(Codes.AuthenticationResponse.AUTHENTICATED);

		return new CustomAuthenticationToken(user.getLoginId(), user, selectedRoleCode, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(CustomAuthenticationToken.class) || authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}