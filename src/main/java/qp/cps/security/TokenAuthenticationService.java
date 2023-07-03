package qp.cps.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import com.google.common.collect.Sets;
//import com.sg.moe.pz.constant.Codes;
//import com.sg.moe.pz.constant.Messages;
//import com.sg.moe.pz.dto.AuthUserDto;
//import com.sg.moe.pz.entity.AaResourcesBean;
//import com.sg.moe.pz.entity.AaSubjectBean;
import qp.cps.model.*;
import qp.cps.dto.*;
import qp.cps.constant.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	protected transient static Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

	static final long EXPIRATIONTIME = 30L * 60L * 1000L;// 864000000; // 1800000; // 30 minutes 864_000_000; // 10 days

//	static final long EXPIRATIONTIME =  10L * 1000L;// 864000000; // 1800000; // 30 minutes 864_000_000; // 20 sec

	static final String SECRET = "ThisIsASecret123";

	static final String TOKEN_PREFIX = "Bearer";
	// static final String TOKEN_PREFIX = "WV-TOKEN";

	static final String ROLE = "Role";

	static final String RESOURCES = "Resources";
	

	public static void addAuthentication(HttpServletRequest request, HttpServletResponse response, Object principal) {
		User user = (User) principal;

		// get the role resource list
		Set<GrantedAuthority> resources = Sets.newHashSet();
//		if (user.getResourceList() != null) {
//			for (AaResourcesBean b: user.getResourceList()) {
//				 resources.add(new CustomGrantedAuthority(b.getResourcePath()));
//			}
//		}

		refreshAuthentication(response, principal, resources);
		String loginId = user.getUsername();

		Authentication authentication = new CustomAuthenticationToken(loginId, principal, user.getRole(), resources);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		getSessionMap(request).put(loginId, authentication);
	}
	

	public static void addAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Set<GrantedAuthority> authorities = Sets.newHashSet(authentication.getAuthorities());

		refreshAuthentication(response, authentication.getPrincipal(), authorities);
		String loginId = user.getUsername();

		SecurityContextHolder.getContext().setAuthentication(authentication);
		getSessionMap(request).put(loginId, authentication);
	}
	
	public static void refreshAuthentication(HttpServletResponse response, Object principal, Collection<GrantedAuthority> authorities) {
		Set<String> uris = Sets.newHashSet();
		for (GrantedAuthority authority : authorities) {
			uris.add(authority.getAuthority());
		}

		User user = (User) principal;
		String role =user.getRole();
		AuthUserDto dto = AuthUserDto.buildFromUser(user);
		long expirationTime = System.currentTimeMillis() + EXPIRATIONTIME;
		String JWT = Jwts.builder().claim(RESOURCES, uris).claim("permissions", dto.getPermissions()).claim("resourcesIds", dto.getResourcesIds()).claim("loginId", user.getUsername()).claim(ROLE, role).setSubject(user.getUsername()).claim("firstName", user.getFirstName()).claim("lastName", user.getLastName()).claim("lastLoginDate", user.getLastLoginDt()).setIssuedAt(new Date())
				.setExpiration(new Date(expirationTime)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		response.setHeader(Codes.Headers.APP_TOKEN, JWT);
	}
	
	@SuppressWarnings("unchecked")
	public static Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = null;
		String token = request.getHeader(Codes.Headers.APP_AUTH);
		if (token == null) {
			logger.error("Token not found");
			throw new AuthenticationCredentialsNotFoundException(Messages.Errors.AUTH_NO_TOKEN);
		} else {
			// parse the token. Take only the first token if there are multiple
			token = token.split(",")[0].replace(TOKEN_PREFIX, "");
			try {
				Claims claims = Jwts.parser().setSigningKey(SECRET).setAllowedClockSkewSeconds(60).parseClaimsJws(token).getBody(); // 30 seconds tolerance
				String loginId = claims.getSubject();

				List<String> uris = (List<String>) claims.get(RESOURCES);
				String selectedRole = (String) claims.get(ROLE);
				logger.info(String.format("User %s (Role %s) is accessing %s", loginId, selectedRole, request.getRequestURI()).replaceAll("[\r\n]", ""));

//				// validate resource
//				boolean grantedAccess = false;
//				for (String grantedUri : uris) {
//					if (grantedUri != null && matches(request.getRequestURI(), grantedUri)) {
//						grantedAccess = true;
//						break;
//					}
//				}
//				
//				if (!grantedAccess) {
//					logger.error(String.format("User %s (Role %s) has no access to %s", loginId, selectedRole, request.getRequestURI()).replaceAll("[\r\n]", ""));
//					throw new InsufficientAuthenticationException(Messages.Errors.AUTH_NO_ACCESS);
//				}

//				Date expiration = claims.getExpiration();
//				if (expiration.before(new Date())) {
//					logger.error("User {} (Role {}) token has expired.", loginId, selectedRole);
//					throw new NonceExpiredException(Messages.Errors.AUTH_EXPIRED);
//
//				}
			
				auth = getSessionMap(request).get(loginId);
				if (auth != null) {
					// session exist, checks if token belongs to an older login (issued before last login date) as multiple login sessions is not allowed
					User user = (User) auth.getPrincipal();
					if (user.getLastLoginDt() != null && claims.getIssuedAt() != null) {
						if (user.getLastLoginDt() != null && claims.getIssuedAt() != null) {
							LocalDateTime issuedDate = LocalDateTime.ofInstant(claims.getIssuedAt().toInstant(), ZoneId.systemDefault());
							LocalDateTime lastLoginDt = LocalDateTime.ofInstant(user.getLastLoginDt().toInstant(), ZoneId.systemDefault());
							if (issuedDate.isBefore(lastLoginDt.withNano(0))) {
								logger.error("User {} (Role {}) uses a token belonging to an older login.", loginId, selectedRole);
								throw new SessionAuthenticationException(Messages.Errors.AUTH_MULTI_SESSIONS);
							}
						}
					}
				} else if (loginId != null) {
					// server restart while user is still browsing with a valid JWT, return empty auth for AuthenticationManager to re-populate
					auth = new CustomAuthenticationToken(loginId, null, selectedRole, null);
				}

			} catch (ExpiredJwtException e) {
				// log which user login has expired
				logger.error("User {} (Role {}) session has expired.", e.getClaims().getSubject(), e.getClaims().get(ROLE));
				throw new NonceExpiredException(Messages.Errors.AUTH_EXPIRED);

			} catch (Exception e /* ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException */) {
				logger.error(e.getMessage(), e);
				throw new AuthenticationServiceException(e.getMessage());
			}
		}
		return (auth == null) ? new UsernamePasswordAuthenticationToken(null, null) : auth;
	}


	@SuppressWarnings("unchecked")
	private static Map<String, Authentication> getSessionMap(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		Map<String, Authentication> sessionMap = ((Map<String, Authentication>) servletContext.getAttribute("SESSIONS"));
		if (sessionMap == null) {
			sessionMap = new HashMap<String, Authentication>();
			servletContext.setAttribute("SESSIONS", sessionMap);
		}
		return sessionMap;
	}
	
	/**
	 * Return when request uri matches the granted function uri
	 * 
	 * Example: if function uri is /pz/users/update request uri like /pz/users/update, /pz/users/update/, /pz/users/update/1 will matched
	 *
	 * @param requestUri
	 * @param functionUri
	 * @return
	 */
	private static boolean matches(String requestUri, String functionUri) {
		if(functionUri.contains("loan-repayment")) {
			functionUri = "/account/loan-repayment";
		}
		return requestUri.matches("\\/app\\/pz\\/protected"+functionUri.replace("/", "\\/") + "(\\/[a-zA-Z0-9\\-\\_\\%\\s]*)*");
	}
}
