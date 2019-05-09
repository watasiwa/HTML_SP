<%@page import="java.util.ArrayList"%>
<%@page import="com.patagonia.model.EmpVO"%>

<%@page import="com.patagonia.dao.EmpDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>patagonia</title>
<script type="text/javascript">
	function fn_insert() {
		location.href = "13_EMP_INS.jsp";
	}


</script>
</head>
<body>
	<table>
		<tr>
			<td>사번</td>
			<td>이름</td>
			<td>폰번호</td>
			<td>이메일</td>
			<td>등록날짜</td>
			<td>등록자</td>
			<td>수정날짜</td>
			<td>수정자</td>
		</tr>
		

<% 		
		EmpDao dao = new EmpDao();
		ArrayList<EmpVO> list = dao.selEmp(null);
		
		for(int i =0; i <list.size(); i++){
			
			String sawon_id = list.get(i).getSawon_id();
			String sawon_name = list.get(i).getSawon_name();
			String mobile = list.get(i).getMobile();
			String email = list.get(i).getEmail();
			String ins_date = list.get(i).getIns_date();
			String ins_id = list.get(i).getIns_id();
			String upd_date = list.get(i).getUpd_date();
			String upd_id = list.get(i).getUpd_id();
%>

		<tr>
			<td>
		 	<a href="13_EMP_DETAIL.jsp?sawon_id=<%=sawon_id%>"><%=sawon_id%></a>
			</td>
			<td><%=sawon_name%></td>
			<td><%=mobile%></td>
			<td><%=email%></td>
			<td><%=ins_date%></td>
			<td><%=ins_id%></td>
			<td><%=upd_date%></td>
			<td><%=upd_id%></td>
		</tr>
<% 
		}
		
%>	
</table>

	<input type="button" onclick="fn_insert()" value="사원등록" >


</body>
</html>