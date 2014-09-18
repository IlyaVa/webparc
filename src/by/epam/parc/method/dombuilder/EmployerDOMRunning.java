package by.epam.parc.method.dombuilder;

import javax.servlet.http.HttpServletRequest;
import by.epam.parc.controller.Command;

public class EmployerDOMRunning implements Command{
		@Override
	public String execute(HttpServletRequest request) {
		EmployerDOMbuilder dombuilder = new EmployerDOMbuilder();
		if(dombuilder.buildSetEmployer("../webparc/source/EmployerVW.xml") == 0){
			dombuilder.buildSetEmployer("../webparc/source/EmployerVW.xml");
			request.setAttribute("dataWorker", dombuilder.getWorkers());
			request.setAttribute("dataSellers", dombuilder.getSellers());
			request.setAttribute("method", "DOMparcing");
		}else
			return "/jsp/error.jsp";
		return "/jsp/out.jsp";
	}
}
