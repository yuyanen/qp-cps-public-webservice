package qp.scs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer extends qp.scs.model.Entity{

	

	@Id
	public String id;

	public String contactPerson;
	
	public String companyName;
	
	public Integer phone;
	
	public String email;
	
	public String address;
	
	public Integer postcode;
	
	public String buildingFloorUnit;
	
	
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getBuidingFloorUnit() {
		return buildingFloorUnit;
	}

	public void setBuidingFloorUnit(String buidingFloorUnit) {
		this.buildingFloorUnit = buidingFloorUnit;
	}

	public Customer() {
		
	}

}
