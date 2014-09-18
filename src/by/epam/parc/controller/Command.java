package by.epam.parc.controller;

import javax.servlet.http.HttpServletRequest;


public interface  Command {
	String execute(HttpServletRequest request);

}
