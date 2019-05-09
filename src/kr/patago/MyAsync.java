package kr.patago;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.patago.service.OmokServiceImpl;
import kr.patago.vo.OmokVO;

/**
 * Servlet implementation class MyAjax
 */
@WebServlet("/myasync")   // 어노테이션
public class MyAsync extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String history =  request.getParameter("history");
		System.out.println(history);
		String status =  request.getParameter("status");
		System.out.println(status);
		
		
		
		
	//	response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		
		PrintWriter out =  response.getWriter();
		
		String text_pre = "[";
        String text = "";
        String text_post = "]";
        
        OmokServiceImpl service = OmokServiceImpl.getInstance();
        
        ArrayList<OmokVO> list = new ArrayList<OmokVO>();
        
        OmokVO vo = new OmokVO();
        vo.setHistory(history);
         ArrayList<OmokVO> historys = (ArrayList<OmokVO>) service.selectListHist(vo);
        
         for(int i =0; i < historys.size(); i++){
        	 String pan       = historys.get(i).getPan();
        	 String pan_order = historys.get(i).getPan_order();
        	 
        	 pan_order = (Integer.parseInt(pan_order)+2)+"";
        	 
        	 OmokVO vo1 = new OmokVO();
        	 vo1.setPan(pan);
        	 vo1.setPan_order(pan_order);
        	 
        	 
        	 ArrayList<OmokVO> nexts = (ArrayList<OmokVO>) service.selectListHistnextOder(vo1);
        	 
        	 
        	 if(nexts.size() > 0){
        		 
        		 String historynext = nexts.get(0).getHistory();
        		 String[] ij = {"-1","-1"};
        		 getIJ(history, historynext, status, ij);
        		 
        		 OmokVO addVO = new OmokVO();
        		 addVO.setI(ij[0]);
        		 addVO.setJ(ij[1]);
        		 addVO.setWinner(nexts.get(0).getWinner());
        		 addVO.setPan(nexts.get(0).getPan());
        		 
        		 
        		 list.add(addVO);
        	 }
        	 
         }
        
        for (int i = 0; i < list.size(); i++) {
           String ii 	 = list.get(i).getI();
           String jj 	 = list.get(i).getJ();
           String winner = list.get(i).getWinner();
           String pan    = list.get(i).getPan();
           text += "{\"ii\" : \""+ii+"\", \"jj\" : \""+jj+"\", \"winner\" : \""+winner+"\", \"pan\" : \""+pan+"\"},";
        }
        if (list.size()>0) {
           text = text.substring(0, text.length() - 1);
        }
        
        out.print(text_pre);
        out.print(text);
        out.print(text_post);
		
		
		//	out.flush();
		System.out.println("doGet");
		
	}

	public void getIJ(String history , String historynext, String status, String[] ij){
		
		int index_ij = -1;
		for(int i=0; i < history.length(); i++){
			String cut = history.substring(i, i+1);
			String cutn = historynext.substring(i, i+1);
		
			if((!cut.equals(cutn)) && status.equals(cutn)){
				index_ij = i;
				break;
			}
		}
		int ii = (index_ij/10);
		int jj = (index_ij%10);
	
		ij[0] = ii+"";
		ij[1] = jj+"";
		
	}
 	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
