package com.ezops.pojo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TITANIC_INFORMATION")
public class Titanic_Info {
	 @Id
	 private String passenger_id;
	 private String	survied;
	 private String	class_of_travel;
	 private String	passenger_firstname;
	 private String passenger_lastname;
	 private String sex;
	 private String age;
	 private String number_of_sibspouse_aboard;
	 private String number_of_parent_aboard;
	 private String ticket;
	 private String fare;
	 private String cabin;
	 private String Embarked;
	 
	 
	public Titanic_Info(String passenger_id, String survied, String class_of_travel, String passenger_firstname,
			String passenger_lastname, String sex, String age, String number_of_sibspouse_aboard,
			String number_of_parent_aboard, String ticket, String fare, String cabin, String embarked) {
		super();
		this.passenger_id = passenger_id;
		this.survied = survied;
		this.class_of_travel = class_of_travel;
		this.passenger_firstname = passenger_firstname;
		this.passenger_lastname = passenger_lastname;
		this.sex = sex;
		this.age = age;
		this.number_of_sibspouse_aboard = number_of_sibspouse_aboard;
		this.number_of_parent_aboard = number_of_parent_aboard;
		this.ticket = ticket;
		this.fare = fare;
		this.cabin = cabin;
		Embarked = embarked;
	}
	public String getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(String passenger_id) {
		this.passenger_id = passenger_id;
	}
	public String getSurvied() {
		return survied;
	}
	public void setSurvied(String survied) {
		this.survied = survied;
	}
	public String getClass_of_travel() {
		return class_of_travel;
	}
	public void setClass_of_travel(String class_of_travel) {
		this.class_of_travel = class_of_travel;
	}
	public String getPassenger_firstname() {
		return passenger_firstname;
	}
	public void setPassenger_firstname(String passenger_firstname) {
		this.passenger_firstname = passenger_firstname;
	}
	public String getPassenger_lastname() {
		return passenger_lastname;
	}
	public void setPassenger_lastname(String passenger_lastname) {
		this.passenger_lastname = passenger_lastname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNumber_of_sibspouse_aboard() {
		return number_of_sibspouse_aboard;
	}
	public void setNumber_of_sibspouse_aboard(String number_of_sibspouse_aboard) {
		this.number_of_sibspouse_aboard = number_of_sibspouse_aboard;
	}
	public String getNumber_of_parent_aboard() {
		return number_of_parent_aboard;
	}
	public void setNumber_of_parent_aboard(String number_of_parent_aboard) {
		this.number_of_parent_aboard = number_of_parent_aboard;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getEmbarked() {
		return Embarked;
	}
	public void setEmbarked(String embarked) {
		Embarked = embarked;
	}
	 
	 
	
}
