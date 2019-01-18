package com.trummer.javarestarttraining.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFirstServlet extends GenericServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 71493582493050630L;

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		res.setContentType("text/html");
		final PrintWriter pw = res.getWriter();
		pw.println("The first servlet by Christian for JavaRestart");
		pw.close();

	}

}
