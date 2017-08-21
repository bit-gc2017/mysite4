<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>

<title>Mysite</title>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<form id="write-form" method="" action="">
						<table>
							<tr>
								<td>이름</td>
								<td><input type="text" name="name" /></td>
								<td>비밀번호</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>

					</form>

					<ul id="guestbook-list">


					</ul>

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->

		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- /container -->


	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->


</body>



<script type="text/javascript">
var lastNo = 0;

/* 게시물을 10개 가져와 화면에 출력합니다. */
var fetchList = function() {

	$.ajax({
		url : "${pageContext.request.contextPath }/api/gb/list",
		type : "post",
		/* contentType : "application/json", */
		data : {lastNo: lastNo},
		dataType : "json",
		success : function(list) {
			for(var i=0; i<list.length; i++){
				render(list[i], "down");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

}


/* 게시물을 화면에 표현합니다. */
var render = function (guestbookVo, updown) {
	
	var str = "";
	str += "<li id='li-"+guestbookVo.no+"' >";
	str += "	<table>";
	str += "		<tr>";
	str += "			<td>[" + guestbookVo.no + "]</td>";
	str += "			<td>" + guestbookVo.name + "</td>";
	str += "			<td>" + guestbookVo.regDate + "</td>";
	str += "			<td><a href='#' data-delno='" + guestbookVo.no + "' >삭제</a></td>";
	str += "		</tr>";
	str += "		<tr>";
	str += "			<td colspan=4>"+ guestbookVo.content.replace(/\n/gi,'<br>') + "</td>";
	str += "		</tr>";
	str += "	</table>";
	str += "<br/>";
		

	if(updown == "up"){
		$("#guestbook-list").prepend(str); 
	}else if(updown == "down"){
		$("#guestbook-list").append(str); 
		lastNo = guestbookVo.no;
	}else{
		console.log("updown 오류")
	}
}


$(document).ready(function() {
	
	fetchList()
	//페이지가 준비될때
	
	//저장버튼을 클릭했을때
 	$("#write-form").on("submit", function() {
		event.preventDefault();
		
		guestbookVo ={
        	name: $("input[name='name']").val(),
        	password: $("input[name='password']").val(),
        	content: $("textarea[name='content']").val()
        }
                    
		$.ajax({
			url : "${pageContext.request.contextPath }/api/gb/add",		
			type : "post",
			/* contentType : "application/json", */
			data : guestbookVo,
			dataType : "json",
			success : function(resultVo) {
				console.log(resultVo)
				if(resultVo.result == "success"){
					render(resultVo.data, "up");
					$("#write-form input").val("");
					$("#write-form textArea").val("");
				}else{
					console.log(resultVo.failMsg);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	});
	
	
	/* 삭제팝업창 띄우기*/ 
	$("#guestbook-list").on("click", "a", function() {
		event.preventDefault();
		var no = $(this).data("delno");
		
		$("#modalNo").val(no);
	    $("#del-pop").modal();
	    
	});

	

	/* 삭제 */
	$("#btn_del").on("click", function() {
		event.preventDefault();
		
		var no = $("#modalNo").val();
    	var password = $("#modalPassword").val();
    	
		
		$.ajax({
			url : "${pageContext.request.contextPath }/api/gb/delete",	
			type : "post",
			contentType : "application/json",
			data : JSON.stringify({no: no, password: password}),
			dataType : "json",
			success : function(resultVo) {
				console.log(resultVo);
				if(resultVo.result == "success"){
					$("#li-"+resultVo.data).remove();	
					$("#del-pop").modal("hide")
					$("#modalPassword").val("");
					$("#modalNo").val("");
				}else{
					console.log(resultVo.failMsg);
					$("#del-pop").modal("hide")
				}	
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
	
		
	});
	
	//스크롤이 화면 제일 아래에 왔을때
	$(window).scroll(function() {
	    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
	    	fetchList();
	    }
	});
	
});	
	

	
	
</script>


</html>

