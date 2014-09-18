package by.epam.parc.method.saxbuilder;


import javax.servlet.http.HttpServletRequest;
import by.epam.parc.controller.Command;

public class EmployerSAXRunning implements Command{	

		@Override
	public String execute(HttpServletRequest request) {
		
			EmployerSAXBuilder saxbuilder = new EmployerSAXBuilder();

		if(saxbuilder.builderSetEmployer("../webparc/source/EmployerVW.xml") == 0){
			request.setAttribute("dataWorker", saxbuilder.getWorkers());
			request.setAttribute("dataSellers", saxbuilder.getSellers());
			request.setAttribute("method", "SAXparcing");
		}else
			return "/jsp/error.jsp";
		return "/jsp/out.jsp";
		
	}

}
