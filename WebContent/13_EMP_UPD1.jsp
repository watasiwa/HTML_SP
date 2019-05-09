>
<%@page import="com.patagonia.dao.EmpDao"%>
<%@page import="com.patagonia.model.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>patagonia</title>
</head>
<script type="text/javascript">
	function fn_save() {
		document.frm.submit();
	}

</script>
<body>
	
	수정화면
	<%
	String sawon_id = request.getParameter("sawon_id");
	
	EmpVO paramVO = new EmpVO();
	paramVO.setSawon_id(sawon_id);
	
	EmpDao dao = new EmpDao();
	EmpVO rVO = dao.ddong(paramVO);
	%>
	
	<form id ="frm" name="frm" method="POST" action="13_EMP_UPD1.ACT.jsp">
	 	
	
	<table>
	
		<tr>
			<td>사번</td>
			<td><input type="text" name="sawon_id" value="<%=rVO.getSawon_id()%>" readonly></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="sawon_name" value="<%=rVO.getSawon_name()%>"></td>
		</tr>
		<tr>
			<td>휴대폰</td>
			<td><input type="text" name="mobile" value="<%=rVO.getMobile()%>"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=rVO.getEmail()%>"></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button" onclick="fn_save()" value="저장">
		
			</td>
		
		</tr>
		
	</table>
	
</form>
	
</body>
</html>