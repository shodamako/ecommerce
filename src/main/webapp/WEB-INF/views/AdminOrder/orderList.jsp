<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/item.css">
<link href="/css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script src="/js/jquery-1.8.3.js"></script>
<script src="/js/jquery-ui-1.9.2.custom.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
		$('.menu').click(function() {
			$('.menuList').slideToggle();
		});
	});
</script>
<title>管理画面：商品詳細</title>
</head>
<body>
	<header>
		<div id="linkHeader">
			<h1 align="left">
				<a href="/Admin/showMenu"> <img src="/img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像"></a>
			</h1>
		</div>
		<div id="userHeader">
			<div class="userHeaderContents">
				<p>
					管理者 <br> [
					<c:out value="${page.loginName }" />
					]さん
				</p>
			</div>
			<div class="userHeaderContents">
				<div class="menu">メニュー▼</div>
				<div class="menuList">
					<ul>
						<li><a href="/Admin/showMenu">メニュー</a></li>
						<li><a href="/Admin/Item">商品管理</a></li>
						<li><a href="/Admin/orderList">注文一覧</a></li>
						<li><a href="/Admin/logout">ログアウト</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
	<hr>
	<div align="center">

		<h1>注文一覧画面</h1>
		<c:if test="${empty orderpage.orderChildPage}">
			<font color="#ff0000">注文はありません。</font>
		</c:if>
		<table border="1">
			<tr>
				<th nowrap>注文番号</th>
				<th nowrap>日付</th>
				<th nowrap>利用者名</th>
				<th nowrap>現在のステータス</th>
				<th nowrap>総計(税込)</th>
			</tr>
			<c:forEach var="order" items="${orderpage.orderChildPage}">
				<tr>
					<td align="right"><a href="/Admin/ShowOrderDetail/${order.id}">
							<c:out value="${order.orderNumber}" /></a></td>
							<td><fmt:formatDate value="${order.date}" pattern="yyyy年MM月dd日" /></td>
							<td><c:out value="${order.name}" /></td>
							<td>
								<c:choose>
									<c:when test="${order.status==1 }">
										未入金
									</c:when>
									<c:when test="${order.status==2 }">
										入金済み
									</c:when>
									<c:when test="${order.status==3 }">
										発送済み
									</c:when>
									<c:when test="${order.status==9 }">
										キャンセル
									</c:when>
								</c:choose></td>
							<td><Div Align="right"><fmt:formatNumber value="${order.totalPrice}" pattern="###,###"/>円</Div></td>
				</tr>
			</c:forEach>
		</table>
		<br> <a href="/Admin/showMenu">メニューに戻る</a>
	</div>
</body>
</html>