package com.patagonia.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patagonia.dao.CompanyDao;
import com.patagonia.dao.EmpDao;
import com.patagonia.model.CompanyVO;
import com.patagonia.model.EmpVO;


@WebServlet("/MyAjaxCheck")
public class MyAjaxCheck extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String co_id = request.getParameter("co_id");
		String message = "";
		
		CompanyVO vo = new CompanyVO();
		vo.setCo_id(co_id);

		CompanyDao dao = new CompanyDao();
		
		try {
			CompanyVO rVO = dao.select(vo);
			
			if( rVO == null){
			   message = "ok";
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		out.print("{\"message\" : \""+message+"\"}");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
