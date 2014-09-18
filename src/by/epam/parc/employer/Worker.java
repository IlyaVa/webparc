package by.epam.parc.employer;

public class Worker extends EmployerVW{
	private int numberOfItem;
	public Worker (){
		super();
	}
	public void setNumberOfItem(int value) {
		
			this.numberOfItem = value;
	}
		
	public int getNumberOfItem(){
		return numberOfItem;
	}
	@Override
	public int earnMoney(int newSalary) {
		int sumMoney;
		sumMoney = numberOfItem*5;
		return sumMoney;
	}
//	public String toString(){
//		return "\nFirstName: "+firstName+"\nSurName: "+surName+"\nCity: "+ city+
//				"\n\tPassword: "+password+"\n\tID: "+iD+"\nNumberOfItem: "+numberOfItem;
//	}

}
