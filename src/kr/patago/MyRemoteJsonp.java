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


@WebServlet("/myremotejsonp")
public class MyRemoteJsonp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int[][] arr2D= new int[10][10];
	String status;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("myremotejsonp");
		
		String history = request.getParameter("history");
		System.out.println("history:"+history);
		status = request.getParameter("status");
		System.out.println("status:"+status);
		
		PrintWriter out = response.getWriter();
		
		
		
		for(int i=0;i<arr2D.length;i++){
			for(int j=0;j<arr2D[i].length;j++){
				String temp = history.substring((i*10+j), (i*10+j)+1);
				arr2D[i][j] = Integer.parseInt(temp);
				
			}
		}
		
		
		Double[][] arr2Dattack= new Double[10][10];
		Double[][] arr2Ddefens= new Double[10][10];
		Double[][] arr2Dcenter= new Double[10][10];
		Double[][] arr2Dsumall= new Double[10][10];
		Double[][] arr2Dmemory= new Double[10][10];
		Double[][] arr2Drandom= new Double[10][10];
		
		for(int i=0;i<arr2Dsumall.length;i++){
			for(int j=0;j<arr2Dsumall[i].length;j++){
				arr2Dattack[i][j] = 0.0d;
				arr2Ddefens[i][j] = 0.0d;
				arr2Dcenter[i][j] = 0.0d;
				arr2Dsumall[i][j] = 0.0d;
				arr2Dmemory[i][j] = 0.0d;
				arr2Drandom[i][j] = 0.0d;
				
			}
		}
		
		for(int i=0;i<arr2D.length;i++){
			for(int j=0;j<arr2D[i].length;j++){

				arr2Drandom[i][j] = (Math.random()*10000)/100000;
		
			}
		}
		
		System.out.println(arr2Dattack[0][0]);

		OmokServiceImpl omokService = OmokServiceImpl.getInstance();
		
		ArrayList<OmokVO> list = new ArrayList<OmokVO>();
		
		OmokVO pVO1 = new OmokVO();
		pVO1.setHistory(history);
		
		ArrayList<OmokVO> historys = (ArrayList<OmokVO>) omokService.selectListHist(pVO1);
		
		for(int i=0;i<historys.size();i++){
			String pan			= historys.get(i).getPan();
			String pan_order	= historys.get(i).getPan_order();
			
			pan_order = (Integer.parseInt(pan_order)+2)+"";
			
			OmokVO pVO2 = new OmokVO();
			pVO2.setPan(pan);
			pVO2.setPan_order(pan_order);
			
			
			ArrayList<OmokVO> nexts = (ArrayList<OmokVO>) omokService.selectListHistnextOder(pVO2);
			
			if(nexts.size()>0){
				String historyNext = nexts.get(0).getHistory();
				String[] ij = {"-1","-1"};
				getIJ(history, historyNext, status, ij);
				
				OmokVO aVO = new OmokVO();
				aVO.setI(ij[0]);
				aVO.setJ(ij[1]);
				aVO.setWinner(nexts.get(0).getWinner());
				aVO.setPan(nexts.get(0).getPan());

				
				list.add(aVO);
				
			}

		}
		
		for(int i=0;i<list.size();i++){
			int ii = Integer.parseInt(list.get(i).getI());
			int jj = Integer.parseInt(list.get(i).getJ());
			if(status.equals(list.get(i).getWinner())){
				arr2Dmemory[ii][jj] += 0.1;
			} else {
				arr2Dmemory[ii][jj] += -0.1;
			}
			
		}
		
		
		for(int i=0;i<arr2D.length;i++){
			for(int j=0;j<arr2D[i].length;j++){
				int red = (i*i)+((i-arr2D.length)*(i-arr2D.length));
				int black = (arr2D.length)*(arr2D.length) - red;
				
				int red_j = (j*j)+((j-arr2D.length)*(j-arr2D.length));
				int black_j = (arr2D.length)*(arr2D.length) - red_j;
				
				arr2Dcenter[i][j] =  (black+black_j)/100000d;
		
			}
		}
		
		
		for(int i=0;i<arr2D.length;i++){
			for(int j=0;j<arr2D[i].length;j++){
				int cnt_top		= fn_top(i,j);
				int cnt_down	= fn_down(i,j);
				
				int cnt_left	= fn_left(i,j);
				int cnt_right	= fn_right(i,j);
				
				int cnt_topright	= fn_topright(i,j);
				int cnt_downleft	= fn_downleft(i,j);
				
				int cnt_topleft		= fn_topleft(i,j);
				int cnt_downright	= fn_downright(i,j); 
				
				int[] arr_cnt = new int[4];
				
				arr_cnt[0] = cnt_top		+cnt_down		+1;
				arr_cnt[1] = cnt_left		+cnt_right		+1;
				arr_cnt[2] = cnt_topright	+cnt_downleft	+1;
				arr_cnt[3] = cnt_topleft	+cnt_downright	+1;
				
				int max = fn_getMaxFromArray(arr_cnt);

				arr2Dattack[i][j] = max*0.99;

			}
		}
		
		
		if(status.equals("1")){
			status = "2";
		} else if(status.equals("2")){
			status = "1";
		}
		
		
		for(int i=0;i<arr2D.length;i++){
			for(int j=0;j<arr2D[i].length;j++){
				int cnt_top		= fn_top(i,j);
				int cnt_down	= fn_down(i,j);
				
				int cnt_left	= fn_left(i,j);
				int cnt_right	= fn_right(i,j);
				
				int cnt_topright	= fn_topright(i,j);
				int cnt_downleft	= fn_downleft(i,j);
				
				int cnt_topleft		= fn_topleft(i,j);
				int cnt_downright	= fn_downright(i,j); 
				
				int[] arr_cnt = new int[4];
				
				arr_cnt[0] = cnt_top		+cnt_down		+1;
				arr_cnt[1] = cnt_left		+cnt_right		+1;
				arr_cnt[2] = cnt_topright	+cnt_downleft	+1;
				arr_cnt[3] = cnt_topleft	+cnt_downright	+1;
				
				int max = fn_getMaxFromArray(arr_cnt);

				arr2Ddefens[i][j] = max+0.0d;

			}
		}	
		
		if(status.equals("1")){
			status = "2";
		} else if(status.equals("2")){
			status = "1";
		}
		
		
		for(int i=0;i<arr2D.length;i++){
			for(int j=0;j<arr2D[i].length;j++){
				arr2Dsumall[i][j] = arr2Dattack[i][j]+arr2Ddefens[i][j]+arr2Dcenter[i][j]+arr2Dmemory[i][j]+arr2Drandom[i][j];
			}
		}
		
		for(int i=0;i<arr2Dsumall.length;i++){
			for(int j=0;j<arr2Dsumall[i].length;j++){
				if(arr2D[i][j] >0 ){
					arr2Dsumall[i][j] = -1d;
				}
			}
		}
		
		double max_all = 0;
		for(int i=0;i<arr2Dsumall.length;i++){
			for(int j=0;j<arr2Dsumall[i].length;j++){
				if(max_all<arr2Dsumall[i][j]){
					max_all = arr2Dsumall[i][j];
				}
			}
		}
		
		int ret_i = -1;
		int ret_j = -1;
		
		for(int i=0;i<arr2Dsumall.length;i++){
			for(int j=0;j<arr2Dsumall[i].length;j++){
				if(arr2Dsumall[i][j]==max_all){
					ret_i = i;
					ret_j = j;
				}
			}
		}
		
		
		
		try {
//			ArrayList<OmokVO> list = (ArrayList<OmokVO>) omokService.selectListPan();
			
			String text_pre = "[";
			String text = "";
			String text_post = "]";
			
			text += "{\"i\":\""+ret_i+"\",\"j\":\""+ret_j+"\"}";
			
			out.print("myCallback(");
			out.print(text_pre);
			out.print(text);
			out.print(text_post);
			out.print(")");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	public void getIJ(String history, String historyNext, String status,String[] ij){
		
		int index_ij = -1;
		for(int i=0;i<history.length();i++){
			String cut	= history.substring(i, i+1);
			String cutN	= historyNext.substring(i, i+1);
			
			if((!cut.equals(cutN)) && status.equals(cutN) ){
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

	}

	
	
	
	
	
	
	
	
	
	
	
	public int fn_top(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i-1;
			j = j;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	public int fn_down(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i+1;
			j = j;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	
	public int fn_left(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i;
			j = j-1;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	
	public int fn_right(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i;
			j = j+1;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	
	
	public int fn_topright(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i-1;
			j = j+1;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	
	public int fn_downleft(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i+1;
			j = j-1;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}

	
	public int fn_topleft(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i-1;
			j = j-1;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	
	public int fn_downright(int i,int j){
		int ret_cnt = 0;
		while(true){
			i = i+1;
			j = j+1;
			try {
				int int_status = Integer.parseInt(status);
				if(arr2D[i][j] == int_status){
					ret_cnt++;
				}else{
					return ret_cnt;
				}
			} catch (Exception e) {
				return ret_cnt;
			}
			
		}

	}
	
	
	public int  fn_getMaxFromArray(int[] arr){
		int max = 0; 
		for(int k=0;k<arr.length;k++){
			if(max<arr[k]){
				max = arr[k];
			}	
		}
		return max;
	
	}
	
	
	
	
}
