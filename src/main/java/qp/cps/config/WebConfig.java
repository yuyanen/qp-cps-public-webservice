package qp.cps.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import qp.cps.constant.Codes;
//import com.sg.moe.pz.interceptor.RequestProcessingTimeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${origin.url}")
	private String[] originUrl;
	
	protected transient static Logger logger = LoggerFactory.getLogger(WebConfig.class);

	private static final String[] CORS_HEADERS_ALLOWED = { 
			Codes.Headers.VERSION_CHECK, 
			Codes.Headers.VERSION_CHECK_LISTING, 
			Codes.Headers.APEX_AUTH, 
			Codes.Headers.APP_AUTH, 
			Codes.Headers.APP_TOKEN,
			Codes.Headers.XSRF_TOKEN, 
			"Accept", "Content-Type", "Origin" };
	
	private static final String[] CORS_HEADERS_EXPOSED = { 
			Codes.Headers.VERSION_CHECK, 
			Codes.Headers.VERSION_CHECK_LISTING, 
			Codes.Headers.APEX_AUTH, 
			Codes.Headers.APP_AUTH, 
			Codes.Headers.APP_TOKEN,
			Codes.Headers.XSRF_TOKEN };
	
	private static final String[] CORS_METHODS_ALLOWED = {  "OPTIONS", "GET", "POST"  };

//	@Autowired
//	RequestProcessingTimeInterceptor requestProcessingTimeInterceptor;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/protected/**")
		.allowedOrigins(originUrl)
		.allowCredentials(false)
		.maxAge(3600)
		.allowedHeaders(CORS_HEADERS_ALLOWED)
		.exposedHeaders(CORS_HEADERS_EXPOSED)
		.allowedMethods(CORS_METHODS_ALLOWED);
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(requestProcessingTimeInterceptor);
//	}

}
