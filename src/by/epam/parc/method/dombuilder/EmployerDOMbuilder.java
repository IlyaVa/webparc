package by.epam.parc.method.dombuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.parc.employer.Seller;
import by.epam.parc.employer.Worker;


public class EmployerDOMbuilder {
	static Logger log = Logger.getLogger(EmployerDOMbuilder.class.getName());
	static {PropertyConfigurator.configure("../webparc/source/log4j.properties");}
	private Set<Worker> worker;
	private Set<Seller> seller;
	private DocumentBuilder docbuilder;
	public EmployerDOMbuilder(){
		this.worker = new HashSet<Worker>();
		this.seller = new HashSet<Seller>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();// сщздание ДОМ анализатора
		try{
				docbuilder = factory.newDocumentBuilder();
		}catch (ParserConfigurationException e) {
			log.error ("Ошибка конфигурации парсера "+ e);
		}
	}
	public Set<Worker> getWorkers(){
		return worker;
	}
	public Set<Seller> getSellers(){
		return seller;
	}
	public int buildSetEmployer(String fileName){
		Document doc = null;
		try{
			doc = docbuilder.parse(fileName);//парсинг документа
			Element root = doc.getDocumentElement();
			NodeList workerList = root.getElementsByTagName("worker");//получение списка дочерних элементов worker
			NodeList sellerList = root.getElementsByTagName("seller");
			for(int i = 0;i <workerList.getLength();i++){
				
				Element workerElement = (Element)workerList.item(i);
				Worker workers = buildWorker(workerElement);
				worker.add(workers);
				}
			for(int i = 0;i <sellerList.getLength();i++){
				
				Element sellerElement = (Element)sellerList.item(i);
				Seller sellers = buildSeller(sellerElement);
				seller.add(sellers);
				}
		}catch (IOException e){
			log.error("File error or I-O error: "+e);
			return 1;
		}catch (SAXException e){
			log.error("Parsing failure: "+e);
			return 1;
		}
		return 0;
	}
private Worker buildWorker (Element workerElement){
	Worker workers = new Worker();
	workers.setId(workerElement.getAttribute("iD"));// complit object workers
	workers.setFirstName(getElementTextContent(workerElement,"firstName"));
	workers.setSurName(getElementTextContent(workerElement,"surName"));
	workers.setCity(getElementTextContent(workerElement,"city"));
	workers.setPassword(getElementTextContent(workerElement,"password"));
	Integer item = Integer.parseInt(getElementTextContent(workerElement,"numberOfitem"));
	workers.setNumberOfItem(item);
		return workers;
}
private Seller buildSeller (Element sellerElement){
	Seller sellers = new Seller();
	sellers.setId(sellerElement.getAttribute("iD"));// complit object workers
	sellers.setFirstName(getElementTextContent(sellerElement,"firstName"));
	sellers.setSurName(getElementTextContent(sellerElement,"surName"));
	sellers.setCity(getElementTextContent(sellerElement,"city"));
	sellers.setPassword(getElementTextContent(sellerElement,"password"));
	Integer client = Integer.parseInt(getElementTextContent(sellerElement,"numberOfclient"));
	sellers.setNumberOfClient(client);
	return sellers;
}
private static String getElementTextContent (Element element,String elementName){//получение тексового элемента
	NodeList nList = element.getElementsByTagName(elementName);
	Node node = nList.item(0);
	String text = null;
	try{
	 text = node.getTextContent();
	}catch (NullPointerException e){
		log.error("Incorrect data: "+e);
	}
	return text;
}

}
