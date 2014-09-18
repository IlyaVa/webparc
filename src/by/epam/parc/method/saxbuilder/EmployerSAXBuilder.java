package by.epam.parc.method.saxbuilder;

import java.io.IOException;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.parc.employer.Seller;
import by.epam.parc.employer.Worker;

public class EmployerSAXBuilder {
	
	static Logger log = Logger.getLogger(EmployerSAXBuilder.class.getName());
	static {PropertyConfigurator.configure("../webparc/source/log4j.properties");}
	private Set<Worker> worker;
	private Set<Seller> seller;
	private EmployerHandler empHand;
	private XMLReader reader;
	public EmployerSAXBuilder(){
		empHand = new EmployerHandler();// создание SAX анализатора

		try{
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(empHand);

		}catch (SAXException e){
			log.error("Ошибка SAX  парсера: " + e);
		}
	}
	public Set<Worker> getWorkers(){
		return worker;
	}
	public Set<Seller> getSellers(){
		return seller;
	}
	public int builderSetEmployer (String filename){
		try{
			reader.parse(filename);

		}catch (SAXException e){
			log.error("Error SAX parse: "+ e);
		}catch (IOException e){
			log.error("Error I/O stream: "+e);
			return 1;
		}
		worker = empHand.getWorkers();
		seller = empHand.getSellers();
		return 0;
	}
}
