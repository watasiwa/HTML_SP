
<%@page import="com.sun.xml.internal.ws.api.ha.StickyFeature"%>
<%@page import="com.patagonia.dao.PataVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.patagonia.dao.OracleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patagonia</title>
</head>
<body>
<table>
	<tr>
		<td>사번</td>
		<td>이름</td>
		<td>연봉</td>
	</tr>
	
<%
	OracleDao dao = new OracleDao();
	ArrayList<PataVO> list =  dao.getPata();
	
	for(int i = 0; i < list.size(); i++){
		String col1 = list.get(i).getCol1();
		String col2 = list.get(i).getCol2();
		String col3 = list.get(i).getCol3();
%>	
	<tr>
		<td><%=col1%></td>
		<td><%=col2%></td>
		<td><%=col3%></td>
	</tr>
<% 		
	}
	
%>		
	
</table>


	
</body>
</html>