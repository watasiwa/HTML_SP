package com.patagonia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OracleDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url    = "jdbc:oracle:thin:@localhost:1521:xe";
	String user   = "patagonia";
	String password = "q1w2e3r4";
	
	public ArrayList<PataVO> getPata() throws Exception {
		
		ArrayList<PataVO> list = new ArrayList<PataVO>();
			
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url    = "jdbc:oracle:thin:@localhost:1521:xe";
		String user   = "patagonia";
		String password = "q1w2e3r4";
		
		Class.forName(driver);
		Connection con =  DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from pata");
		
		while(rs.next()){
			String col1 = rs.getNString(1);
			String col2 = rs.getNString(2);
			String col3 = rs.getNString(3);
			
			PataVO vo = new PataVO();
			vo.setCol1(col1);
			vo.setCol2(col2);
			vo.setCol3(col3);
			
			list.add(vo);
		}
		rs.close();
		stmt.close();
		con.close();
		
		
		return list;
	}
	
	public int insPata(PataVO vo) throws Exception{
		String query    = "insert into pata (col1,col2,col3) values('"+vo.getCol1() +"','"+vo.getCol2() +"','"+vo.getCol3() +"')";
		
		Class.forName(driver);
		Connection con =  DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		int cnt = stmt.executeUpdate(query);
		
		System.out.println("cnt : "+ cnt);
	
		stmt.close();
		con.close();
		
		return cnt;
	}
	
	public int updPata(PataVO vo) throws Exception{
		String query    = "update pata set col2 = '"+vo.getCol2()+"',"
										+ "col3 = '"+vo.getCol3()+"' where col1='"+vo.getCol1()+"'";
		
		Class.forName(driver);
		Connection con =  DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		int cnt = stmt.executeUpdate(query);
		
		System.out.println("cnt : "+ cnt);
	
		stmt.close();
		con.close();
		
		return cnt;
		
	}
	
	public int delPata(PataVO vo) throws Exception{
		
		String query    = "delete from pata where col1='"+vo.getCol1()+"'";
		
		Class.forName(driver);
		Connection con =  DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		int cnt = stmt.executeUpdate(query);
		
		System.out.println("cnt : "+ cnt);
	
		stmt.close();
		con.close();
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		OracleDao dao = new OracleDao();
		//select절
//		ArrayList<PataVO> list = dao.getPata();
//		
//		for(int i =0; i < list.size(); i++){
//			String col1 = list.get(i).getCol1();
//			String col2 = list.get(i).getCol2();
//			String col3 = list.get(i).getCol3();
//			
//		}
//		for(PataVO vo : list){
//			System.out.println(vo);
//		}
		// insert절
//		PataVO vo = new PataVO();
//		vo.setCol1("1001");
//		vo.setCol2("홍길동");
//		vo.setCol3("2억");
//		
//		int cnt = dao.insPata(vo);
//		
//		System.out.println(cnt + "개 적용되었습니다.");
//	
	
//		//update절
//		PataVO vo = new PataVO();
//		vo.setCol1("1001");
//		vo.setCol2("루피");
//		vo.setCol3("4억");
//		
//		int cnt = dao.updPata(vo);
//		
//		System.out.println(cnt + "개 적용되었습니다.");
		
		// delete절
		PataVO vo = new PataVO();
		vo.setCol1("1001");
		
		int cnt = dao.delPata(vo);
		
		System.out.println(cnt + "개 삭제되었습니다.");
		
	}
	
}
