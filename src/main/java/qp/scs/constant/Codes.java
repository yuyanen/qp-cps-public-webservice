package qp.scs.constant;


public interface Codes {

	public static interface Headers {
		public static final String VERSION_CHECK = "Version-Check";
		public static final String VERSION_CHECK_LISTING = "Version-Check-Listing";
		public static final String XSRF_TOKEN = "X-XSRF-TOKEN";
		public static final String APP_TOKEN = "X-Auth-Token";
		public static final String APP_AUTH = "App-Authorization";
		public static final String APEX_AUTH = "Authorization";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String BASIC_AUTH = "Authorization";

		public static interface Values {
			public static final String APPLICATION_JSON = "application/json";
		}
	}
	
	public static interface AuthenticationResponse {
		public static final String UNAUTHENTICATED = "UNAUTHENTICATED";
		public static final String AUTHENTICATED = "AUTHENTICATED";
		public static final String PASSWORD_INCORRECT = "PASSWORD_INCORRECT";
		public static final String USER_NO_ACCESS = "USER_NO_ACCESS";
		public static final String OTP_INCORRECT = "OTP_INCORRECT";
		public static final String OTP_INVALID_FORMAT = "OTP_INVALID_FORMAT";
		public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
		public static final String USER_INACTIVE = "USER_INACTIVE";
		public static final String USER_ACCT_LOCKED = "USER_ACCT_LOCKED";
		public static final String SSO_HEADER_NOT_FOUND = "SSO_HEADER_NOT_FOUND";
	}
	
	public static interface LoginType {
		public static final String PASSWORD = "PASSWORD";
		public static final String AUTO_LOGIN = "AUTO_LOGIN";
		public static final String MIMS = "MIMS";
	}
	
	public static interface LoginStatus {
		public static final String SUCCESS = "Success";
		public static final String LOGOUT = "Logout";
		public static final String INVALID_ACC = "Invalid login name";
		public static final String INACTIVE_ACC = "Account is inactive";
	}

}
