package com.ezops.pojo;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TITANIC_INFORMATION")
public class Titanic_Info {
	 @Id
	 private String PassengerId;
	 private String	Survied;
	 private String	Pclass;
	 private String	Name;
	 private String Sex;
	 private String Age;
	 private String SibSp;
	 private String Parch;
	 private String Ticket;
	 private String Fare;
	 private String Cabin;
	 private String Embarked;
	 
	 
	 
	public Titanic_Info() {
		super();		
	}	
	public String getPassengerId() {
		return PassengerId;
	}
	public void setPassengerId(String passengerId) {
		PassengerId = passengerId;
	}
	public String getSurvied() {
		return Survied;
	}
	public void setSurvied(String survied) {
		Survied = survied;
	}
	public String getPclass() {
		return Pclass;
	}
	public void setPclass(String pclass) {
		Pclass = pclass;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getSibSp() {
		return SibSp;
	}
	public void setSibSp(String sibSp) {
		SibSp = sibSp;
	}
	public String getParch() {
		return Parch;
	}
	public void setParch(String parch) {
		Parch = parch;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getFare() {
		return Fare;
	}
	public void setFare(String fare) {
		Fare = fare;
	}
	public String getCabin() {
		return Cabin;
	}
	public void setCabin(String cabin) {
		Cabin = cabin;
	}
	public String getEmbarked() {
		return Embarked;
	}
	public void setEmbarked(String embarked) {
		Embarked = embarked;
	}
	 
	 
	 
	
	
}
