package qp.cps.security;

import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private String name;

	private String roleCode;

	public CustomAuthenticationToken(String name, Object principal, String roleCode, Set<GrantedAuthority> authoritie) {
		super(principal, null,authoritie);
		this.name = name;
		this.roleCode = roleCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
