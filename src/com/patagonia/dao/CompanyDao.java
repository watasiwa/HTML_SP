package com.patagonia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patagonia.model.CompanyVO;

public class CompanyDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url    = "jdbc:oracle:thin:@localhost:1521:xe";
	String user   = "patagonia";
	String password = "q1w2e3r4";
	
	
	
	public CompanyVO select(CompanyVO vo) throws Exception{
		
		String query = "";
		
		query += "select ";
		query += "    com.co_id, ";
		query += "    com.co_name, ";
		query += "    com.manager_id, ";
		query += "    com.stock_yn, ";
		
		query += "    com.co_tel, ";
		query += "    com.co_ceo_name, ";
		query += "    com.intime, ";
		query += "    com.inid, ";
		query += "    com.uptime, ";
		
		query += "    com.upid, ";
		query += "   e.sawon_name as manager_name ";
		query += "  from ";
		query += "  company com, emp e";
		query += "  where ";
		query += "  com.manager_id = e.sawon_id      ";
		query += "  and ";
		query += "  com.co_id ="+vo.getCo_id() +" ";
	
		
		System.out.println(query);
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
	
		
		CompanyVO resultvo = null;		
		
		while(rs.next()){
			String co_id       = rs.getString(1);
			String co_name     = rs.getString(2);
			String manager_id  = rs.getString(3);
			String stock_yn    = rs.getString(4);
			String co_tel      = rs.getString(5);
			String co_ceo_name = rs.getString(6);
			String intime      = rs.getString(7);
			String inid 	   = rs.getString(8);
			String uptime 	   = rs.getString(9);
			String upid 	   = rs.getString(10);
			String manager_name = rs.getString(11);
			
			resultvo = new CompanyVO();
			
			resultvo.setCo_id(co_id);
			resultvo.setCo_name(co_name);
			resultvo.setManager_id(manager_id);
			resultvo.setStock_yn(stock_yn);
			resultvo.setCo_tel(co_tel);
			resultvo.setCo_ceo_name(co_ceo_name);
			resultvo.setIntime(intime);
			resultvo.setInid(inid);
			resultvo.setUptime(uptime);
			resultvo.setUpid(upid);
			resultvo.setManager_name(manager_name);
		}
		rs.close();
		stmt.close();
		conn.close();
		
		
		return resultvo;
		
	}
	
	
	
	
	
	
	
	public ArrayList<CompanyVO> selectlist() throws Exception{
		String query = "";
		
		query += "select ";
		query += "   com.co_id, ";
		query += "   com.co_name, ";
		query += "   com.manager_id, ";
		
		query += "   com.stock_yn, ";
		query += "   com.co_tel, ";
		query += "   com.co_ceo_name, ";
		query += "   com.intime, ";
		query += "   com.inid, ";
		query += "   com.uptime, ";
		query += "   com.upid, ";
		query += "   e.sawon_name as manager_name ";
		query += "from ";
		query += "  company com, emp e  ";
		query += "where ";
		query += "  com.manager_id = e.sawon_id ";
	
		
		System.out.println(query);
		
		ArrayList<CompanyVO> list = new ArrayList<CompanyVO>();
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			CompanyVO vo = new CompanyVO();
			
			vo.setCo_id(rs.getString(1));
			vo.setCo_name(rs.getString(2));
			vo.setManager_id(rs.getString(3));
			vo.setStock_yn(rs.getString(4));
			vo.setCo_tel(rs.getString(5));
			vo.setCo_ceo_name(rs.getString(6));
			vo.setIntime(rs.getString(7));
			vo.setInid(rs.getString(8));
			vo.setUptime(rs.getString(9));
			vo.setUpid(rs.getString(10));
			vo.setManager_name(rs.getString(11));
			list.add(vo);
		}
		rs.close();
		stmt.close();
		conn.close();
		
		return list;
	}
	
	public int insert(CompanyVO vo) throws Exception{
		String query = "";
		
		query += "insert into company";
		query += "  ( ";
		query += " co_id, ";
		query += " co_name, ";
		query += " manager_id, ";
		query += " stock_yn, ";
		query += " co_tel, ";
		query += " co_ceo_name, ";
		query += " intime, ";
		query += " inid, ";
		query += " uptime, ";
		query += " upid ";
		query += "  ) ";
		query += " values ";
		query += " ( ";
		query += " '"+ vo.getCo_id()+"'," ;
		query += " '"+ vo.getCo_name()+"'," ;
		query += " '"+ vo.getManager_id()+"'," ;
		query += " '"+ vo.getStock_yn()+"'," ;
		query += " '"+ vo.getCo_tel()+"'," ;
		query += " '"+ vo.getCo_ceo_name()+"'," ;
		query += " sysdate," ;
		query += " '"+ vo.getManager_id()+"'," ;
		query += " sysdate," ;
		query += " '"+ vo.getManager_id()+"'" ;
		query += "  ) ";
		
		System.out.println(query);
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,password);
		PreparedStatement pstmt = conn.prepareStatement(query);
		int cnt = pstmt.executeUpdate(query);
		
		
		pstmt.close();
		conn.close();
		
		return cnt;
		
	}
	public int update(CompanyVO vo) throws Exception{
		  String sql = ""; 
	      sql = "update company ";
	      sql += "set ";
	      sql += "co_id = "+vo.getCo_id()+", ";
	      sql   += "co_name = '"+vo.getCo_name()+"', ";
	      sql   += "manager_id = "+vo.getManager_id()+", ";
	      sql   += "stock_yn = '"+vo.getStock_yn()+"', ";
	      
	      sql += "co_tel = '"+vo.getCo_tel()+"', ";
	      sql   += "co_ceo_name = '"+vo.getCo_ceo_name()+"', ";
	      sql   += "uptime = sysdate, ";
	      sql   += "upid = "+vo.getCo_id()+" ";
	      sql += "where ";
	      sql += "co_id = "+vo.getCo_id()+" ";
	      
	      System.out.println(sql);
	      Class.forName(driver);
	      Connection con = DriverManager.getConnection(url,user,password);
	      Statement stmt = con.createStatement();
	      int cnt = stmt.executeUpdate(sql);
	      
	      stmt.close();
	        con.close();
	      return cnt;
	   }
	public int delete(CompanyVO vo) throws Exception{
		String query = "delete from company where co_id='" + vo.getCo_id() +"'";
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,password);
		PreparedStatement pstmt = conn.prepareStatement(query);
		int cnt = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
			
		return cnt;
	}
	public static void main(String[] args) throws Exception {
		CompanyDao dao = new CompanyDao();
		CompanyVO vo = new CompanyVO();

		// select절
		
		ArrayList<CompanyVO> list = dao.selectlist();
/*		
		for(int i =0; i <list.size(); i++){
			
			String col1 = list.get(i).getCo_id();
			String col2 = list.get(i).getCo_name();
			String col3 = list.get(i).getManager_id();
			String col4 = list.get(i).getStock_yn();
			String col5 = list.get(i).getCo_tel();
			String col6 = list.get(i).getCo_ceo_name();
			String col7 = list.get(i).getIntime();
			String col8 = list.get(i).getInid();
			String col9 = list.get(i).getManager_name();
			
			System.out.println("사원번호 : " + col1 + "\n" +
							   "이    름 : " + col2 + "\n" +
							   "담당번호 : " + col3 + "\n" +
							   "주    식 : " + col4 + "\n" +
							   "전화번호 : " + col5 + "\n" +
							   "사장이름 : " + col6 + "\n" +
							   "업데날짜 : " + col7 + "\n" +
							   "업데번호 : " + col8 + "\n" +
							   "담당자명 : " + col9 + "\n"
							   );
		}*/
	
		
		// insert
/*		vo.setCo_id("2");
		vo.setCo_name("바보1");
		vo.setManager_id("999");
		vo.setStock_yn("n");
		vo.setCo_tel("010-2222-3333");
		vo.setCo_ceo_name("나다리");
		vo.setInid("999");
		vo.setUpid("999");
		
		int cnt = dao.insert(vo);
		System.out.println("cnt : " + cnt);
		
		*/
		
		// update
		
		/*vo.setCo_id("2");
		vo.setCo_name("바보2");
		vo.setManager_id("999");
		vo.setStock_yn("y");
		vo.setCo_tel("010-1004-3333");
		vo.setCo_ceo_name("나다리");
		vo.setInid("999");
		vo.setUpid("999");
		
		int cnt = dao.update(vo);
		System.out.println("cnt : " + cnt);
		 */
		
		
		// select 절
		
		vo.setCo_id("1");
		CompanyVO vo1 = dao.select(vo);
		System.out.println(vo1.getCo_ceo_name());
		
		
		// delete 절
	/*	vo.setCo_id("2");
		
		int cnt = dao.delete(vo);
		System.out.println("cnt : " + cnt);
	*/
	}
}
