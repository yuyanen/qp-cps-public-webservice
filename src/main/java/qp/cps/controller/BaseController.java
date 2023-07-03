package qp.cps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import qp.cps.model.*;


/*
 *  DTO - Customize for you need to return as response
 *  Controller  - [Module Name]Controller.java               (eg. in frontend have account-enquiry module, create EnquiryController)
 *  Service     - [Module Name]Service.java       (eg. EnquiryService.java, to share among controller)
 *  ServiceImpl - [Module Name]ServiceImpl.java   (eg. EnquiryServiceImpl.java, where the logic should be placed. Easy sharing.)
 *  Repository  - [Table Name, without PZ]Repository.java    (eg. CodeIntRepository.java , IntNsraRepository.java  , NsraTopUpRepository.java)
 *  Entity      - [Table Name, without PZ]Bean    (eg. PseaMasterBean)
 */

	

public class BaseController {
	
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
}
