package com.bridgelabz.user.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="UserDetails")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotNull(message="Please Enter Valid First Name")
	@NotEmpty(message="First Name should Not be Empty")
	private String firstName;
	
	@NotNull(message="Please Enter Valid Last Name")
	@NotEmpty(message="Last Name should Not be Empty")
	private String lastName;

	@Email(message="PLEASE PROVIDE VALID EMAIL ID")
	@Column(unique=true)
	@NotNull(message="Please Enter Valid Email")
	@NotEmpty(message="Email should Not be Empty")
	private String email;

	@NotNull(message="Please Enter Valid Password")
	@Size(min=6, message="Password Must Contain min 6 characters")
	@NotEmpty(message="Password should Not be Empty")
	private String password;

	@NotNull(message="Please Enter Valid Contact Number")
	private long contactNumber;

	@Value("false")
	private Boolean verified;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", contactNumber=" + contactNumber + ", verified=" + verified + "]";
	}
}