<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/replyboard/write" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<c:forEach items="${list }" var="vo">
						<tr>
							<td>${vo.no }</td>
							<td style="text-align:left">
								<c:forEach begin="1" end="${vo.depth }">
									&nbsp;&nbsp;&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${vo.depth > 0}">
									<img src="${pageContext.request.contextPath }/assets/images/reply.png">
								</c:if>
								<c:if test="${vo.state eq 'del'}">
									삭제된 게시물 입니다.
								</c:if>
								<c:if test="${vo.state ne 'del'}">
									<a href="${pageContext.request.contextPath }/replyboard/read?no=${vo.no }">${vo.title }</a>
								</c:if>
								
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>
								g:${vo.groupNo} &nbsp;&nbsp;&nbsp;&nbsp;   o:${vo.orderNo} &nbsp;&nbsp;&nbsp;&nbsp;   d:${vo.depth}
							</td>
							<%-- <td>${vo.regDate }</td> --%>
							<td>
								<c:if test="${authUser.no == vo.userNo }">
									<a href="${pageContext.request.contextPath }/replyboard/delete?no=${vo.no }" class="del">삭제</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li><a href="">5</a></li>
						<li><a href="">6</a></li>
						<li><a href="">7</a></li>
						<li><a href="">8</a></li>
						<li><a href="">9</a></li>
						<li><a href="">10</a></li>
						<li><a href="">▶</a></li>
					</ul>
				</div>				
				<c:if test="${authUser != null }">
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/replyboard/writeform" id="new-book">글쓰기</a>
					</div>
				</c:if>		
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div><!-- /container -->
</body>
</html>		
		