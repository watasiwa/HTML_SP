
<%@page import="com.patagonia.model.EmpVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.patagonia.dao.EmpDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>patagonia</title>
</head>
<script src="jquery-1.12.4.js"></script>
<script type="text/javascript">
	function fn_save() {
	
	var co_name = document.getElementsByName("co_name")[0].value;
	var manager_id = document.getElementsByName("manager_id")[0].value;
	
	var co_tel = document.getElementsByName("co_tel")[0].value;
	var co_ceo_name = document.getElementsByName("co_ceo_name")[0].value;
	
	if(co_name == ""){
		alert("협력사 명은 기입하셔야 합니다.");
		document.getElementsByName("co_name")[0].focus();
		return;
	}
	
	if(manager_id == ""){
		alert("담당자 명은 기입하셔야 합니다.");
		document.getElementsByName("manager_id")[0].focus();
		return;
	}
			
	if(co_tel == ""){
		alert("회사 연락처는 기입하셔야합니다.");
		document.getElementsByName("co_tel")[0].focus();
		return
	}
	
	if(co_ceo_name ==""){
		alert("마 니네 대표 누구고, 적어야할꺼 아니가!");
		document.getElementsByName("co_ceo_name")[0].focus();
		return;
	}
	
		document.frm.submit();
	
	
		var regExp = /^\d{3}-\d{3,4}-\d{4}$/;
		
		if ( !regExp.test(co_tel.val())) {

		      alert("잘못된 휴대폰 번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.");
			  co_tel.focus();
			  return false

		}


		
	
	
	
	}
	
	function fn_check() {
		
		var co_id = $("input[name=co_id]").val();
		if(co_id == ""){
			alert("협력사번호는 입력되어야 합니다.");
			$("input[name=co_id]").focus();
			return;
		}
		
		var int_co_id = Number(co_id); 
		
		var flag = Number.isInteger(int_co_id);
		
			if(!flag){
				alert("협력사 번호는 숫자 타입이어야 합니다.");
				return;
			}
		
		$.ajax({

			type : "get",
			url : "MyAjaxCheck",
			data : { co_id : co_id},
			dataType : "json",



			success : function(data) {
				
				if(data.message == "ok"){
					$("input[name=co_id]").attr("readyonly",true);
					alert("사용할 수 있는 아이디입니다.")
				}else{
					$("input[name=co_id]").val("");	
					$("input[name=co_id]").val("").focus();	
					alert("사용할 수 없는 아이디입니다.")
					
				}
			},
			
			error : function(xhr, status, error) {
				console.log("에러!: " + error);

				},
			});		
	}
	
	
</script>
<body>
	
	등록화면
	<form id ="frm" name="frm" method="POST" action="14_COMPANY_INS_ACT.jsp">
	 	
	
	<table>2
	
		<tr>
			<td>협력사번호</td>
			<td>
			<input type="text" name="co_id" value=" ">
			<input type="button" value="중복체크" onclick="fn_check()">
			</td>
		</tr>
		<tr>
			<td>협력사명</td>
			<td><input type="text" name="co_name" value=""></td>
		</tr>
		<tr>
					
			<td >담당자ID</td>
			<td>
				<select name="manager_id">

					<option value="">------</option>

					<% EmpDao dao = new EmpDao();
					   ArrayList<EmpVO> listCombo = dao.selEmp(null);
						
					   for(int i = 0; i< listCombo.size(); i++){ %>
					
						<option value="<%=listCombo.get(i).getSawon_id()%>"><%=listCombo.get(i).getSawon_name()%></option>
					
					<%
					   }
					%>				
				</select>
			</td>
		</tr>
		
		<tr>
			<td>주식회사여부</td>
			<td>
				<input type="radio" name="stock_yn" value="Y" checked>주식회사
				<input type="radio" name="stock_yn" value="N" >기타
			</td>
			
		</tr>
		<tr>
			<td>회사연락처</td>
			<td><input type="text" name="co_tel" value=""></td>
		</tr>
		<tr>
			<td>회사대표자</td>
			<td><input type="text" name="co_ceo_name" value=""></td>
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