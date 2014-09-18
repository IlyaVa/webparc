package by.epam.parc.method.staxbuilder;

import javax.servlet.http.HttpServletRequest;


//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import by.epam.parc.controller.Command;

public class EmployerStaxRunning implements Command {
	
	@Override
	public String execute(HttpServletRequest request) {
		EmployerStAXbuilder staxbuilder = new EmployerStAXbuilder();
		if(staxbuilder.buildSetEmployer("../webparc/source/EmployerVW.xml") == 0){
			staxbuilder.buildSetEmployer("../webparc/source/EmployerVW.xml");
			request.setAttribute("dataWorker", staxbuilder.getWorkers());
			request.setAttribute("dataSellers", staxbuilder.getSellers());
			request.setAttribute("method", "StaxParcing");
		}else
			return "/jsp/error.jsp";
		return "/jsp/out.jsp";
	}

}
