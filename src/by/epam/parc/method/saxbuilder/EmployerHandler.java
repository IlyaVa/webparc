package by.epam.parc.method.saxbuilder;


import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.parc.employer.Seller;
import by.epam.parc.employer.Worker;

public class EmployerHandler extends DefaultHandler{
	static Logger log = Logger.getLogger(EmployerHandler.class.getName());

private Set<Worker> worker;
private Worker currentwork;
private Set<Seller> seller;
private Seller currentsel;
private EmployerEnum currentEnum;
private EnumSet <EmployerEnum> withText;
private int n ;
public EmployerHandler(){
	worker = new HashSet<Worker>();
	seller = new HashSet<Seller>();
	withText = EnumSet.range(EmployerEnum.FIRSTNAME, EmployerEnum.NUMBEROFCLIENT);
}
public Set<Worker> getWorkers (){
	return worker;
}
public Set<Seller> getSellers(){
	return seller;
}
public void startElement (String url, String localName, String qName, Attributes attrs){
	if ("worker".equals(localName)){
			currentwork = new Worker();
			currentwork.setId(attrs.getValue(0));
			n = 1;
	}else if ("seller".equals(localName)){
			currentsel = new Seller();
			currentsel.setId(attrs.getValue(0));
	}else{
		try{
			EmployerEnum temp = EmployerEnum.valueOf(localName.toUpperCase());
			if(withText.contains(temp)){
				currentEnum = temp;
			}
		}catch (IllegalArgumentException e){
			log.error("Unknow tag :"+e);
		}
		}
	}
public void endElement(String uri, String localName, String qName){
	if ("worker".equals(localName)){
		worker.add(currentwork);
		n = 0;
	}else if ("seller".equals(localName)){
			seller.add(currentsel);
		}
}
public void characters (char []ch, int start, int length){
	String s = new String (ch, start,length).trim();
		if(currentEnum != null)
			if (n == 1){
			switch (currentEnum){
			case FIRSTNAME: 
				currentwork.setFirstName(s);
				break;
			case SURNAME:
						currentwork.setSurName(s);
				break;
			case CITY: 
						currentwork.setCity(s);
				break;
			case PASSWORD:
						currentwork.setPassword(s);
				break;
			case NUMBEROFITEM: currentwork.setNumberOfItem(Integer.parseInt(s));
				break;
			default:
				try{
					throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
				}catch (EnumConstantNotPresentException e){
					log.error("Unknown element in tag Worker");
				}
			}
			}else{
			switch (currentEnum){
				case FIRSTNAME: 
					currentsel.setFirstName(s);
					break;
				case SURNAME:
					currentsel.setSurName(s);
					break;
				case CITY: 
					currentsel.setCity(s);
					break;
				case PASSWORD:
					currentsel.setPassword(s);
					break;
				case NUMBEROFCLIENT: currentsel.setNumberOfClient(Integer.parseInt(s));
				break;
				default:
					try{
						throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
					}catch (EnumConstantNotPresentException e){
						log.error("Unknown element in tag Worker");
					}
			}
		}
		currentEnum = null;
	}
}
