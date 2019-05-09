package kr.patago;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.patago.dao.OmokDaoImpl;
import kr.patago.service.OmokServiceImpl;
import kr.patago.vo.OmokVO;

/**
 * Servlet implementation class MyOmok
 */
@WebServlet("/myomok")
public class MyOmok extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String historys =  request.getParameter("historys");
		String winner =  request.getParameter("winner");
		System.out.println(historys);
		System.out.println(winner);
		
		String[] arr_history = historys.split(",");
		
		OmokServiceImpl omokService = OmokServiceImpl.getInstance();
		OmokVO maxVO =  omokService.selectMax();
		
		String pan = maxVO.getPan();
		
		
		int count = 0;
		
		for(int i = 0; i < arr_history.length; i++ ){
			String pan_order = (i+1)+"";
			String  history = arr_history[i];
		
			OmokVO paramVO = new OmokVO();
			paramVO.setPan(pan);
			paramVO.setPan_order(pan_order);
			paramVO.setHistory(history);
			paramVO.setWinner(winner);
			
			count  += omokService.insert(paramVO);
		}
		
		String msg = "ok"+","+ pan;
		if(count != arr_history.length){
			msg = "NG";
		}
		
		
	//	response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		
		PrintWriter out =  response.getWriter();
		out.print("{\"message\" : \""+msg+"\"}");
	//	out.flush();
		System.out.println("doPost");

	
	}

}
