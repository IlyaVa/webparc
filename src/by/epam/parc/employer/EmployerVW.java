package by.epam.parc.employer;

public abstract class EmployerVW {
protected String firstName;
protected String surName;
protected String iD;
protected String city;
protected String password;

public String getFirstName(){
	return firstName;
}
public String getSurName(){
	return surName;
}
public String getID(){
	return iD;
}
public String getPassword(){
	return password;
}
public String getCity(){
	if(city == null)
		return "Minsk";
	return city;
}
public void setCity (String value){
	this.city = value;
}
public void setFirstName (String value){
	this.firstName = value;
}
public void setSurName (String value){
	this.surName = value;
}
public void setId (String value){
	this.iD = value;
}
public void setPassword (String value){
	this.password = value;
}
abstract int earnMoney(int newSalary);
}
