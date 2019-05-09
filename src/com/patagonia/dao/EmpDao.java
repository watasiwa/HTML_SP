package com.patagonia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.patagonia.model.EmpVO;

public class EmpDao {
   String driver   = "oracle.jdbc.driver.OracleDriver";
   String url      = "jdbc:oracle:thin:@localhost:1521:xe";
   String user      = "patagonia";
   String password = "q1w2e3r4";
   
   public EmpVO ddong(EmpVO vo) throws Exception{
      String query   = "";
      
      query += "select";
      query += "   sawon_id, ";
      query += "   sawon_name, ";
      query += "   mobile, ";
      query += "   email, ";
      query += "   ins_date, ";
      query += "   ins_id, ";
      query += "   upd_date, ";
      query += "   upd_id ";
      query += "from ";
      query += "   emp ";
      query += "where "; 
      query += "   sawon_id = '"+vo.getSawon_id()+"' ";
      
      Class.forName(driver);
      Connection con = DriverManager.getConnection(url, user, password);
       Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        EmpVO resultvo = new EmpVO();
        
        while(rs.next()) {
           
           resultvo.setSawon_id(rs.getString(1));
           resultvo.setSawon_name(rs.getString(2));
           resultvo.setMobile(rs.getString(3));
           resultvo.setEmail(rs.getString(4));
           
           resultvo.setIns_date(rs.getString(5));
           resultvo.setIns_id(rs.getString(6));
           resultvo.setUpd_date(rs.getString(7));
           resultvo.setUpd_id(rs.getString(8));
        
        }
        rs.close();
       stmt.close();
       con.close();
      
      return resultvo;
   }
   
   
   public int insEmp(EmpVO vo) throws Exception{
      String query   = "";
      
      query += "insert into emp ";
      query += "( ";
      query += "sawon_id,sawon_name,mobile,email, ";
      query += "ins_date,ins_id,upd_date,upd_id ";
      query += ") ";
      query += "values ";
      query += "( ";
      query += "'"+vo.getSawon_id()+"','"+vo.getSawon_name()+"','"+vo.getMobile()+"','"+vo.getEmail()+"', ";
      query += "sysdate,'"+vo.getSawon_id()+"',sysdate,'"+vo.getSawon_id()+"' ";
      query += ") ";
      
      
      Class.forName(driver);
      Connection con = DriverManager.getConnection(url,user,password);
      Statement stmt = con.createStatement();
      int cnt = stmt.executeUpdate(query);
      
      stmt.close();
        con.close();
      return cnt;
   }
   
   public int updEmp(EmpVO vo) throws Exception{
      String query = "";
      
      query +="update emp ";
      query +="set ";
      query +="sawon_name = '"+vo.getSawon_name()+"', ";
      query +="mobile = '"+vo.getMobile()+"', ";
      query +="email = '"+vo.getEmail()+"',  ";
      query +="upd_date = sysdate, ";
      query +="upd_id = '"+vo.getSawon_id()+"' ";
      query +="where ";
      query +="sawon_id = '"+vo.getSawon_id()+"' ";
      
      Class.forName(driver);
      Connection con = DriverManager.getConnection(url,user,password);
      Statement stmt = con.createStatement();
      int cnt = stmt.executeUpdate(query);
      
      stmt.close();
        con.close();
      return cnt;
   }

   public int delEmp(EmpVO vo) throws Exception{
      String query   = "";
      
      query = "delete from emp where sawon_id ='"+vo.getSawon_id()+"'";
      
      Class.forName(driver);
      Connection con = DriverManager.getConnection(url,user,password);
      Statement stmt = con.createStatement();
      int cnt = stmt.executeUpdate(query);
      
      stmt.close();
        con.close();
      return cnt;
   }
   
   public ArrayList<EmpVO> selEmp(EmpVO vo) throws Exception{
      ArrayList<EmpVO> list = new ArrayList<EmpVO>();
      String query   = "";
      
      query += "select";
      query += "   sawon_id, ";
      query += "   sawon_name, ";
      query += "   mobile, ";
      query += "   email, ";
      query += "   ins_date, ";
      query += "   ins_id, ";
      query += "   upd_date, ";
      query += "   upd_id ";
      query += "from ";
      query += "   emp ";
      query += "order by 1"; // 정렬
      
      Class.forName(driver);
      Connection con = DriverManager.getConnection(url, user, password);
       Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while(rs.next()) {
           
           EmpVO vo2 = new EmpVO();
           vo2.setSawon_id(rs.getString(1));
           vo2.setSawon_name(rs.getString(2));
           vo2.setMobile(rs.getString(3));
           vo2.setEmail(rs.getString(4));
           vo2.setIns_date(rs.getString(5));
           vo2.setIns_id(rs.getString(6));
           vo2.setUpd_date(rs.getString(7));
           vo2.setUpd_id(rs.getString(8));
        
          list.add(vo2);
        }
        rs.close();
       stmt.close();
       con.close();
      
      return list;
   }
   
   public static void main(String[] args) throws Exception{
      EmpDao dao = new EmpDao();
      EmpVO vo = new EmpVO();
      /*ArrayList<EmpVO> list = dao.selEmp();
      System.out.println(list.size());
      
      for (EmpVO vo3 : list) {
         System.out.printf("sawon_id : %s sawon_name : %s mobile : %s email : %s"
                     + " ins_date : %s ins_id : %s upd_date : %s upd_id : %s\n",
                        vo3.getSawon_id(),vo3.getSawon_name(),vo3.getMobile(),vo3.getEmail(),
                        vo3.getIns_date(),vo3.getIns_id(),vo3.getUpd_date(),vo3.getUpd_id());
      }*/
      
      
      /*vo.setSawon_id("5");
      vo.setSawon_name("박기훈");
      vo.setMobile("010-3333-3333");
      vo.setEmail("brother2@naver.com");
      vo.setIns_id("0");
      vo.setUpd_id("0");
        
      int cnt = dao.insEmp(vo);
      System.out.println(cnt + "개 적용되었습니다");*/
      
      vo.setSawon_id("1");
       vo.setSawon_name("데헷");
       vo.setMobile("010-1234-4321");
       vo.setEmail("rr@naver.com");
       vo.setUpd_id("1");
        
       int cnt = dao.updEmp(vo);
       System.out.println(cnt + "개 적용되었습니다");
      
      /*vo.setSawon_id("4");
      int cnt = dao.updEmp(vo);
      System.out.println(cnt + "개 적용되었습니다");*/
      
      
      
   }
}