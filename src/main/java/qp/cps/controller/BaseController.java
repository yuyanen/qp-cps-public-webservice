package qp.cps.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import qp.cps.model.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import qp.cps.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import qp.cps.helper.CacheHelper;
/*
 *  DTO - Customize for you need to return as response
 *  Controller  - [Module Name]Controller.java               (eg. in frontend have account-enquiry module, create EnquiryController)
 *  Service     - [Module Name]Service.java       (eg. EnquiryService.java, to share among controller)
 *  ServiceImpl - [Module Name]ServiceImpl.java   (eg. EnquiryServiceImpl.java, where the logic should be placed. Easy sharing.)
 *  Repository  - [Table Name, without PZ]Repository.java    (eg. CodeIntRepository.java , IntNsraRepository.java  , NsraTopUpRepository.java)
 *  Entity      - [Table Name, without PZ]Bean    (eg. PseaMasterBean)
 */

	

public class BaseController {
	
	protected transient Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	protected CacheHelper cache;
	
//	@Autowired
//	protected Properties properties;
//	
	/**
	 * Gets the cached user record of the user making the request, along with the granted Roles
	 * 
	 * @return AuthenticatedUserDTO
	 */
	protected User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		
		return user;
	}
	
	protected void setInAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.setAuthenticated(false);
	}
	
	/**
	 * Handle all generic exceptions
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDto handleAppException(Exception e) {
		long errTs = new Date().getTime();
		logger.error(e.getMessage() + ". ref: " + errTs, e);
		StringBuilder stringBuilder = new StringBuilder();
	
		logger.error(stringBuilder.toString());
		String message = "A Generic Error has occurred. ref: " + errTs;
		return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

}
