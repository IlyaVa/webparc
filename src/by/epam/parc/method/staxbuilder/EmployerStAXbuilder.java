package by.epam.parc.method.staxbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import by.epam.parc.employer.Seller;
import by.epam.parc.employer.Worker;
import by.epam.parc.method.saxbuilder.EmployerEnum;

public class EmployerStAXbuilder {
	static Logger log = Logger.getLogger(EmployerStAXbuilder.class.getName());
	static {PropertyConfigurator.configure("../webparc/source/log4j.properties");}
	private HashSet<Worker> workers = new HashSet<Worker>();
	private HashSet<Seller> sellers = new HashSet<Seller>();
	private XMLInputFactory inputFactory;
	public EmployerStAXbuilder (){
		inputFactory = XMLInputFactory.newInstance();
	}
	public Set<Worker> getWorkers(){
		return workers;
	}
	public Set<Seller> getSellers(){
		return sellers;
	}
	public int buildSetEmployer (String fileName){
		FileInputStream inputStream = null;
		XMLStreamReader reader;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while(reader.hasNext()){ // stax -parsing
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT){
					name = reader.getLocalName();
					if (EmployerEnum.valueOf(name.toUpperCase()) == EmployerEnum.WORKER){
						Worker work = buildWorker(reader);
						workers.add(work);
					}else if (EmployerEnum.valueOf(name.toUpperCase()) == EmployerEnum.SELLER){
						Seller sell = buildSeller(reader);
						sellers.add(sell);
					}
				}
			}
		} catch (XMLStreamException ex) {
			log.error("StAX parsing error! "+ex);
			return 1;
		}catch (FileNotFoundException f){
			log.error("File "+ fileName +" not found!"+ f);
			return 1;
		}finally {
			try{
				if (inputStream != null){
					inputStream.close();
				}
			}catch (IOException i){
				log.error("Impossible close file "+fileName + " : "+i);
			}
		}
		return 0;
	}
	private Worker buildWorker (XMLStreamReader reader){
		Worker work = new Worker();
		work.setId(reader.getAttributeValue(null, EmployerEnum.ID.getValue()));
		String name;
		try {
			while(reader.hasNext()){
				int type = reader.next();
				switch (type){
				case XMLStreamConstants.START_ELEMENT:
					name = reader.getLocalName();
					switch (EmployerEnum.valueOf(name.toUpperCase())){
						case FIRSTNAME: 
							work.setFirstName(getXMLText(reader));
							break;
						case SURNAME: 
							name = getXMLText(reader);
							work.setSurName(name);
							break;
						case PASSWORD: 
							name = getXMLText(reader);
							work.setPassword(name);
							break;
						case CITY: work.setCity(getXMLText(reader));
							break;
						case NUMBEROFITEM: work.setNumberOfItem(Integer.parseInt(getXMLText(reader)));
							break;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					name = reader.getLocalName();
					if(EmployerEnum.valueOf(name.toUpperCase()) == EmployerEnum.WORKER){
						return work;
					}
					break;
				}
			}
		} catch (XMLStreamException  e) {
			log.error("Unknown element in tag Worker"+e);
		}catch (IllegalArgumentException e) {
			log.error("Unknown element in tag Worker"+e);
		}
		return work;
	}
	private Seller buildSeller (XMLStreamReader reader){
		
		Seller sell = new Seller();
		
		sell.setId(reader.getAttributeValue(null, EmployerEnum.ID.getValue()));
		String name;
		try {
			while(reader.hasNext()){
				int type = reader.next();
				switch (type){
				case XMLStreamConstants.START_ELEMENT:
					name = reader.getLocalName();
					switch (EmployerEnum.valueOf(name.toUpperCase())){
						case FIRSTNAME: 
							sell.setFirstName(getXMLText(reader));
							break;
						case SURNAME: 
							name = getXMLText(reader);
							sell.setSurName(name);
							break;
						case PASSWORD: 
							name = getXMLText(reader);
							sell.setPassword(name);
							break;
						case CITY: sell.setCity(getXMLText(reader));
							break;
						case NUMBEROFCLIENT: sell.setNumberOfClient(Integer.parseInt(getXMLText(reader)));
							break;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					name = reader.getLocalName();
					if(EmployerEnum.valueOf(name.toUpperCase()) == EmployerEnum.SELLER){
						return sell;
					}
					break;
					}
				}
			} catch (XMLStreamException e) {
				log.error("Unknown element in tag Seller"+e);
			}catch (NumberFormatException  e) {
				log.error("Unknown element in tag Seller"+e);
			}
		return sell;
	}
	
	private String getXMLText (XMLStreamReader reader) throws  XMLStreamException {
		String text =null;
		if (reader.hasNext()){
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
