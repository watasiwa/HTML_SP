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
	 String dan = request.getParameter("dan");
	 int num	= Integer.parseInt(dan);

// 	 for(int i =1; i<=9; i++){
// 		out.println(num + "*" + i + " = " + (num)*i + "<br/>");	 
		
//	 }
%>
<%for(int i=1;i<=9;i++){%>
<%=num + "*" + i + " = " + (num)*i + "<br/>"%>
<% }%>

</body>
</html>