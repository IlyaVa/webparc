package by.epam.parc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.parc.method.dombuilder.EmployerDOMRunning;
import by.epam.parc.method.saxbuilder.EmployerSAXRunning;
import by.epam.parc.method.staxbuilder.EmployerStaxRunning;
import by.epam.parc.controller.Command;
/**
 * Servlet implementation class adminController
 */
@WebServlet("/adminController")
public class AdminController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
String operation = request.getParameter("parcmethod");
		
		Command com = null;
		if(operation == null)
		{
			response.sendRedirect("/jsp/out.jsp");
		}
		else if(operation.equals("sax"))
		{
			com = new EmployerSAXRunning();
		}
		else if(operation.equals("stax"))
		{
			com = new EmployerStaxRunning();
		}
		else if(operation.equals("dom"))
		{
			com = new EmployerDOMRunning();
		}
		String page = null;
			page = com.execute(request);
			if (page != null) {
				RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher(page);
				dispatcher.forward(request, response);
			} else {
				page = "/index.jsp";
			}

	}

}
