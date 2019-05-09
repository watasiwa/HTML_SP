
<%@page import="com.patagonia.dao.CompanyDao"%>
<%@page import="com.patagonia.model.CompanyVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String co_id   		  = request.getParameter("co_id");
	
	CompanyVO paramVO = new CompanyVO();
	paramVO.setCo_id(co_id);
	
	CompanyDao dao = new CompanyDao();
	int cnt = dao.delete(paramVO);
%>
    
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>patagonia</title>
</head>
<script type="text/javascript">
	
	var cnt = <%=cnt%>;
	
	if(cnt == 1 ){
		location.href = "14_COMPANY_SEL.jsp";   
	}else{
		history.go(-1);						// 이것은 그냥 뒤로가기?
	//	location.href = "13_EMP_UPD1.jsp";  // 서버로 보내주기때문에 파라미터가 없어서 500에러가 남
	}
</script>
<body>
	
수정실행화면

</body>
</html2>