<%@page import="com.patagonia.dao.PataVO"%>
<%@page import="com.patagonia.dao.OracleDao"%>
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
		
		OracleDao dao = new OracleDao();
		PataVO vo = new PataVO();
		
		vo.setCol1(col1);
		vo.setCol2(col2);
		vo.setCol3(col3);
		
		int cnt = dao.updPata(vo);
%>
<%=cnt%>개의 행이 적용되었습니다.
</body>
</html>2