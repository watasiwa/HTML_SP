package com.patagonia.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyAjax
 */
@WebServlet("/MyAjax")   // 어노테이션
public class MyAjax extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param1 =  request.getParameter("param1");
		System.out.println(param1);
		
		
		
		
	//	response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		
		PrintWriter out =  response.getWriter();
		out.print("{\"message\" : \"ok\"}");
	//	out.flush();
		System.out.println("doGet");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
