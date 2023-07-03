package qp.cps.constant;

public class Messages {

	public static interface Errors {
		public static final String AUTH_NO_TOKEN = "You do not have a valid login session. Please login again.";
		public static final String AUTH_NO_ACCESS = "You are not authorised to access this function.";
		public static final String AUTH_MULTI_SESSIONS = "You have another login session. You may close this browser and continue with your latest login session.";
		public static final String AUTH_EXPIRED = "Your session has expired. Please login again.";
		public static final String LOGIN_NO_ACCESS = "You do not have access to the system.";
	}

}
