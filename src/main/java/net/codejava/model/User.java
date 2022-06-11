package net.codejava.model;

import java.beans.Transient;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String username; // Adhar ID

	private String firstname;
	private String lastname;

	private String email;
	private String password;
	private String gender;
	
	private Date birthday;
	private String mobileno;

	
	private String photos;
	private String adhaarpdf;
	private String address;
	private String state;
	private String city;
	private String zip;

	private String role;

	//0=closed
	//1=open
	//2=finished
	// create a service method
	private String votestatus;


	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}
	

	public String getVotestatus() {
		return this.votestatus;
	}

	public void setVotestatus(String votestatus) {
		this.votestatus = votestatus;
	}


	@Transient
    public String getPhotosImagePath() {
        if (photos == null || username == null) return null;
        
        return "/user-photos/" + username + "/" + photos;
    }

	@Transient
    public String getAdhaarPdfPath() {
        if (adhaarpdf == null || username == null) return null;
        
        return "/user-photos/" + username + "/" + adhaarpdf;
    }

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAdhaarpdf() {
		return this.adhaarpdf;
	}

	public void setAdhaarpdf(String adhaarpdf) {
		this.adhaarpdf = adhaarpdf;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", adhaarpdf=" + adhaarpdf + ", birthday=" + birthday + ", city=" + city
				+ ", email=" + email + ", firstname=" + firstname + ", gender=" + gender + ", lastname=" + lastname
				+ ", mobileno=" + mobileno + ", password=" + password + ", photos=" + photos + ", role=" + role
				+ ", state=" + state + ", username=" + username + ", votestatus=" + votestatus + ", zip=" + zip + "]";
	}

}
