package qnu.cntt.dacky.service.dto;

import java.util.UUID;

import qnu.cntt.dacky.domain.Account;

public class AccountInfoDTO {
	private UUID uuid;
	private String username;
	private String course;
	private String department;
	private String className;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public AccountInfoDTO(Account account)
	{
		this.uuid=account.getUUID();
		this.username=account.getUsername();
		this.course=account.getClass1().getCourseAndDepartment().getCourse().getCourse();
		this.department=account.getClass1().getCourseAndDepartment().getDepartment().getDepartmentName();
		this.className=account.getClass1().getName();
		this.firstName=account.getFirstName();
		this.lastName=account.getLastName();
		this.email=account.getEmail();
		this.phoneNumber=account.getPhoneNumber();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
