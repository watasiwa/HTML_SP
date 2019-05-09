<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patagonia</title>
</head>
<body>
<% 
String col1 = request.getParameter("col1");
String col2 = request.getParameter("col2");
String col3 = request.getParameter("col3");

String driver   = "oracle.jdbc.driver.OracleDriver";
String url      = "jdbc:oracle:thin:@localhost:1521:xe";
String user     = "patagonia";
String password = "q1w2e3r4";
String query    = "update pata set col1 = '"+col1+"',col2 = '"+col2+"',col3 = '"+col3+"' where col1='"+col1+"'";

Class.forName(driver);
Connection con =  DriverManager.getConnection(url, user, password);
Statement stmt = con.createStatement();
int cnt = stmt.executeUpdate(query);

if(cnt > 0){
	
%>
 정상처리 되었습니다.	
<%
}

stmt.close();
con.close();	
%>

</body>
</html>