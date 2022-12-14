<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含 base标签 css样式 jQuery样式--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteitem").click(function (){
				return confirm("你确定要删除《"+$(this).parent().parent().find("td:first").text()+"》吗？");
			});

			$("a.clearItem").click(function (){
				return confirm("确定要清空购物车吗？")
			});

			$(".updataCount").blur(function (){
				var value=this.value;
				var id=$(this).attr("bookid");

				if ( confirm("是否确认修改"+$(this).parent().parent().find("td:first").text()+"的数量到"+value)){
					location.href="http://localhost/book/cs?action=upDataCount&bookid="+id+"&count="+value;
				}else {
					this.value=this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<c:if test="${not empty sessionScope.cart.items}">
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			</c:if>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5" ><a href="index.jsp">亲，当前购物车为空</a></td>
				</tr>
			</c:if>

			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updataCount"
							   style="width: 80px"
							   type="text"
							   value="${entry.value.count}"
							   bookid="${entry.value.id}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteitem" href="cs?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>


			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.tltalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a class="clearItem" href="cs?action=clearItem">清空购物车</a></span>
				<span class="cart_span"><a href="os?action=creatOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>