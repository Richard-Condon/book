<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--静态包含 base标签 css样式 jQuery样式--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){

			$("button.addToCart").click(function (){
				if (${empty sessionScope.user}){
					location.href="pages/user/login.jsp";
				}else {
					var bookid=$(this).attr("bookid");
					$.getJSON("cs","action=addItem&id="+bookid,function (data){
						$("#totalCount").text("您的购物车中有"+data.tltalCount+"件商品");
						$("#lastname").text("您刚刚将《"+data.lastname+"》加入到了购物车中");

					});
				}


			});
		});
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<%--如果没登陆显示登陆注册--%>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>

				<%--如果登陆了，则显示后台和购物车信息--%>

				<c:if test="${not empty sessionScope.user}">

					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
					<a href="pages/order/order.jsp">我的订单</a>
					<a href="us?action=logout">注销</a>&nbsp;&nbsp;
					<a href="pages/cart/cart.jsp">购物车</a>
					<a href="pages/manager/manager.jsp">后台管理</a>
				</c:if>

			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookservlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<c:if test="${sessionScope.cart.tltalCount!=0}">
				<div style="text-align: center">
					<span id="totalCount"></span>
					<div id="lastname">

					</div>
				</div>
			</c:if>


			<c:forEach items="${requestScope.page.items}"  var="book" >
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button bookid="${book.id}" class="addToCart">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>



	
	</div>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>
	<%@include file="/pages/common/foot.jsp"%>

</body>
</html>