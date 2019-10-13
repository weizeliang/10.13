<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章详情</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	
</script>
</head>

<!-- False -->
<body>

	<div class="container" align="center">
		<div>
			
			 <!-- <button type="button" class="btn btn-info" onclick="myup()" id="myup">上一篇</button>

			<button type="button" class="btn btn-info" onclick="mydown()"
				id="mydown">下一篇</button> -->
		</div>
		<!-- 文章内容及评论 -->
		
		
		
		
		
		<c:if test="${article!=null}">
			<div>  
			
				<dl>
					<dt>
						<h1>${article.title }</h1>
					</dt>
					<dd>${article.user.nickname}
						&nbsp;
						<fmt:formatDate value="${article.updated }"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</dd>
					<hr
						style="height: 1px; border: none; border-top: 1px dashed #0066CC;" />
					<dd>${article.content }</dd>
				</dl>
				<div style=" border: 1px black solid; width: 400px">
					<p style="background: gray;">点击排行榜</p>
					<ol>
						<li><a href="/article/selectByUser?id=57">${article.title }</a> </li>
						<li><a href="/article/selectByUser?id=60">${article.title }</a> </li>
						<li><a href="/article/selectByUser?id=17">${article.title }</a> </li>
					</ol>
				</div>
				<div style=" border: 1px black solid; width: 400px">
					<p style="background: gray;">评论排行榜</p>
					<ol>
						<li><a href="/article/selectByUser?id=19">${article.title }</a> </li>
						<li><a href="/article/selectByUser?id=27">${article.title }</a> </li>
						<li><a href="/article/selectByUser?id=23">${article.title }</a> </li>
					</ol>
				</div>
				
				<hr />


				<div>
					<h1>文章评论</h1>

					<c:if test="${sessionScope.user!=null }">
						<div>

							<form id="form1">
								<textarea rows="10" cols="155" name="content">
		 </textarea>
								<input type="hidden" name="article.id" value="${article.id }">
								<input type="button" class="btn btn-info" value="评论"
									onclick="addComment()">
							</form>
						</div>
					</c:if>
					<c:if test="${sessionScope.user==null }">
						<span style="color: red">请登录后再评论</span>
					</c:if>
					<!-- 文章评论  -->

					<dl>
						<c:forEach items="${comments}" var="c">
							<dt style="text-align: left;">${c.user.nickname }
								&nbsp;
								<fmt:formatDate value="${c.created}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</dt>
							<dd style="text-align: left;">${c.content }</dd>
							<hr />
						</c:forEach>
						${pages }

					</dl>


				</div>
			</div>
		</c:if>
		<div></div>
	</div>
	
	

</body>
<script type="text/javascript">
	/* function myup() {//前一篇
		//执行方法前要检查 文章存不存在
		$.get("/article/checkPre",{id:'${article.id}',channelId:'${article.channelId}'},function(flag){
			if(flag){//如果前一篇有内容则执行查询
				location.href = "/article/selectByPre?id=" + '${article.id}'
				+ "&channelId=" + '${article.channelId}'
				
			}else{
				alert("别点了,到头了")
			}
		})
		

	}
	function mydown() {
		//执行方法前要检查 文章存不存在
		$.get("/article/checkSuf",{id:'${article.id}',channelId:'${article.channelId}'},function(flag){
			if(flag){//如果下一篇有内容则执行查询
				location.href = "/article/selectBySuf?id=" + '${article.id}'
				+ "&channelId=" + '${article.channelId}'
			}else{
				alert("别点了,到头了")
			}
		})
		

	} */

	//提交评论
	function addComment() {
		$.post("/article/comment", $("#form1").serialize(), function(flag) {
			if (flag) {
				alert("评论成功");
				location.reload();//刷新当前页面
			} else {
				alert("评论失败,检查是否登录");
			}
		})
	}

	$(function() {
		$(".page-link").click(function() {
			var url = $(this).attr('data');
			location.href = url + "&id=" + '${article.id}';

		})

	})
</script>


</html>