package qp.scs.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.sg.moe.pz.entity.AaResourcesBean;
//import com.sg.moe.pz.entity.AaSubjectBean;
import qp.scs.model.*;

public class AuthUserDto {

	private String loginId;
	
	private  List<String> permissions;
	
	private  List<String> resourcesIds;
	
	private String firstName;
	
	private String lastName;
	
	private Date lastLoginDate;
		
	public static AuthUserDto buildFromUser(User subject) {
		AuthUserDto dto = new AuthUserDto();
		dto.setLoginId(subject.getUsername());
		dto.setFirstName(subject.getFirstName()!= null ?subject.getFirstName():"");
		dto.setLastName(subject.getLastName()!= null ?subject.getLastName():"");
		dto.setLastLoginDate(subject.getLastLoginDt());
		
//		if(subject.getResourceList() != null) {
//			dto.setPermissions(new ArrayList<String>());
//			dto.setResourcesIds(new ArrayList<String>());
//			for (AaResourcesBean b :subject.getResourceList()) {
//				dto.getPermissions().add(b.getResourcePath());
//				dto.getResourcesIds().add(b.getResourcesId());
//			}
//		}
		return dto;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public List<String> getResourcesIds() {
		return resourcesIds;
	}

	public void setResourcesIds(List<String> resourcesIds) {
		this.resourcesIds = resourcesIds;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}
