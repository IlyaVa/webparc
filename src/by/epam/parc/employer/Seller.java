package by.epam.parc.employer;

public class Seller extends EmployerVW {
	private int numberOfClient;
	
	public Seller (){
		super();
		}
	public void setNumberOfClient(int value) {
		
		this.numberOfClient = value;
	}
	
	public int getNumberOfClient(){
		return numberOfClient;
	}
	@Override
	public int earnMoney(int newSalary) {
		int sumMoney;
		sumMoney = numberOfClient*5;
		return sumMoney;
	}
	public String toString(){
		return "\nFirstName: "+firstName+"\nSurName: "+surName+"\nCity: "+ city+
				"\n\tPassword: "+password+"\n\tID: "+iD+"\nNumberOfClient: "+numberOfClient;
	}

}


