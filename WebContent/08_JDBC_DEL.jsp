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


String driver   = "oracle.jdbc.driver.OracleDriver";
String url      = "jdbc:oracle:thin:@localhost:1521:xe";
String user     = "patagonia";
String password = "q1w2e3r4";
String query    = "delete from pata where col1='"+col1+"'";

Class.forName(driver);
Connection con =  DriverManager.getConnection(url, user, password);
Statement stmt = con.createStatement();
int cnt = stmt.executeUpdate(query);

%>
<%=cnt + "개의 행이 삭제되었습니다."%>

<% 
stmt.close();
con.close();
%>

</body>
</html>