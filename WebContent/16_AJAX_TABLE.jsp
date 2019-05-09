<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Patagonia</title>
	<script src="jquery-1.12.4.js"></script>
	<script type="text/javascript">
	
	function fn_ajax(){
		$.ajax({

			type : "get",
			url : "http://127.0.0.1/SP/MyAjaxTable",
			data : { param1: 'merryChristmax'},
			dataType : "json",



			success : function(data) {
				
				renderTable(data);
			},
			error : function(xhr, status, error) {
				console.log("에러!: " + error);

			},
		});		
	}
	
	function renderTable(data){
		var text = "";
		for(var i = 0; i < data.length; i++){
			text += "<tr>";
			text += "	<td>"+data[i].sawon_id+"</td>";
			text += "	<td>"+data[i].sawon_name+"</td>";
			text += "</tr>";
		}
		$("#my_tbody").html(text);
		
	}


	
	
	
	</script>
</head>
<body>
	<a href="javascript:fn_ajax()">flickering</a>
	<table>
		<tr>
			<td>사번</td>
			<td>이름</td>
		</tr>
		<tbody id ="my_tbody">
			<tr>
				<td>1</td>
				<td>홍길동</td>
			</tr>		
		</tbody>
	</table>

</body>
</html>