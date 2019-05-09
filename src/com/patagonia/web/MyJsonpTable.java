package com.patagonia.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patagonia.dao.EmpDao;
import com.patagonia.model.EmpVO;

/**
 * Servlet implementation class MyAjaxTable
 */
@WebServlet({ "/MyJsonpTable"})
public class MyJsonpTable extends HttpServlet {
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setCharacterEncoding("utf-8");
      PrintWriter out = response.getWriter();
      
      EmpDao dao = new EmpDao();
      try {
         ArrayList<EmpVO> list = dao.selEmp(null);
//         list.get(1).getSawon_id();
//         list.get(1).getSawon_name();
//         list.get(1).getEmail();
         
         String text_pre = "[";
         String text = "";
         String text_post = "]";
         
         for (int i = 0; i < list.size(); i++) {
            String sawon_id = list.get(i).getSawon_id();
            String sawon_name = list.get(i).getSawon_name();
            
            text += "{\"sawon_id\" : \""+sawon_id+"\", \"sawon_name\" : \""+sawon_name+"\"},";
         }
         if (list.size()>0) {
            text = text.substring(0, text.length() - 1);
         }
         
         out.print("myCallback(");
         out.print(text_pre);
         out.print(text);
         out.print(text_post);
         out.print(")");
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }

}