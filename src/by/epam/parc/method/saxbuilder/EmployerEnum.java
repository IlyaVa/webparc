package by.epam.parc.method.saxbuilder;

public enum EmployerEnum {
EMPLOYERS("employers"),
WORKER("worker"),
SELLER("seller"),
ID("iD"),
FIRSTNAME("firstName"),
SURNAME("surName"),
PASSWORD("password"),
CITY("city"),
NUMBEROFITEM("numberOfitem"),
NUMBEROFCLIENT("numberOfclient");

private String value;
	private EmployerEnum (String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
}
