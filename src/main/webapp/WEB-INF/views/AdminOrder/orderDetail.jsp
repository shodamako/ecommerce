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
<title>管理画面：注文詳細</title>
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
		<h1>注文詳細画面</h1>
		<table border="1">
			<tr>
				<th nowrap>注文NO</th>
				<td><c:out value="${ShowOrderDetailPage.orderNumber}" /></td>
			</tr>
			<tr>
				<th nowrap>名前</th>
				<td><c:out value="${ShowOrderDetailPage.user.name}" /></td>
			</tr>
			<tr>
				<th nowrap>アドレス</th>
				<td><c:out value="${ShowOrderDetailPage.user.email}" /></td>
			</tr>
			<tr>
				<th nowrap>住所</th>
				<td><c:out value="${ShowOrderDetailPage.user.address}" /></td>
			</tr>
			<tr>
				<th nowrap>TEL</th>
				<td><c:out value="${ShowOrderDetailPage.user.telephone}" /></td>
			</tr>
		</table>
		<br>

		<table border="1">
			<tr>
				<th nowrap>商品</th>
				<th nowrap>価格</th>
				<th nowrap>個数</th>
				<th nowrap>金額</th>
			</tr>
			<c:forEach var="child" items="${ShowOrderDetailPage.orderItemList}">
				<tr>
					<td><c:out value="${child.item.name}" /></td>
					<td align="right"><fmt:formatNumber value="${child.item.price}"
							pattern="###,###" />円</td>
					<td align="center"><c:out value="${child.quantity}" /></td>
<<<<<<< HEAD
					<td><fmt:formatNumber
=======
					<td align="right"><fmt:formatNumber
>>>>>>> feature/login
							value="${child.item.price * child.quantity}" pattern="###,###" />円</td>
				</tr>
			</c:forEach>
		</table>
		<br>


		<table border="1">
			<tr>
				<th nowrap>小計</th>
<<<<<<< HEAD
				<td><fmt:formatNumber value="${ShowOrderDetailPage.totalPrice/1.08}"
=======
				<td align="right"><fmt:formatNumber value="${ShowOrderDetailPage.totalPrice / 1.08}"
>>>>>>> feature/login
						pattern="###,###" />円</td>
			</tr>
			<tr>
				<th nowrap>税</th>
				<td align="right"><fmt:formatNumber
<<<<<<< HEAD
						value="${ShowOrderDetailPage.totalPrice /1.08* 0.08}" pattern="###,###" />円</td>
=======
						value="${ShowOrderDetailPage.totalPrice /1.08 * 0.08}" pattern="###,###" />円</td>
>>>>>>> feature/login
			</tr>
			<tr>
				<th nowrap>支払い方法</th>
				<td align="right">銀行振込</td>
			</tr>
			<tr>
				<th nowrap>送料一律</th>
				<td align="right">500円</td>
			</tr>
			<tr>
				<th nowrap>総計</th>
				<td align="right"><fmt:formatNumber
<<<<<<< HEAD
						value="${ShowOrderDetailPage.totalPrice+ 500}"
=======
						value="${ShowOrderDetailPage.totalPrice + 500}"
>>>>>>> feature/login
						pattern="###,###" />円</td>
			</tr>
		</table>
		<br>


		<table border="1">
			<tr>
				<th nowrap>現在のステータス</th>
				<th nowrap>ステータス変更</th>
			</tr>
			<tr>
				<td><c:if test="${ShowOrderDetailPage.status==1}">未入金</c:if> <c:if
						test="${ShowOrderDetailPage.status==2}">入金済み</c:if> <c:if
						test="${ShowOrderDetailPage.status==3}">発送済み</c:if> <c:if
						test="${ShowOrderDetailPage.status==9}">キャンセル</c:if></td>
				<td><form:form modelAttribute="orderStatusForm"
						action="/Admin/ShowOrderDetail/UpdateStatus">
						<select name="status">
						<c:choose>
							<c:when test="${ShowOrderDetailPage.status==1}">
								<option value="1" selected>未入金</option>
								<option value="2" >入金済み</option>
								<option value="3" >発送済み</option>
								<option value="9" >キャンセル</option>
							</c:when>
							<c:when test="${ShowOrderDetailPage.status==2}">
								<option value="1" >未入金</option>
								<option value="2" selected>入金済み</option>
								<option value="3" >発送済み</option>
								<option value="9" >キャンセル</option>
							</c:when>
							<c:when test="${ShowOrderDetailPage.status==3}">
								<option value="1" >未入金</option>
								<option value="2" >入金済み</option>
								<option value="3" selected>発送済み</option>
								<option value="9" >キャンセル</option>
							</c:when>
							<c:when test="${ShowOrderDetailPage.status==9}">
								<option value="1" >未入金</option>
								<option value="2" >入金済み</option>
								<option value="3" >発送済み</option>
								<option value="9" selected>キャンセル</option>
							</c:when>
						</c:choose>
						</select>
						<input type="hidden" name="id" value="${ShowOrderDetailPage.id}">
						<input class="btn" type="submit" value="更新">
					</form:form></td>
			</tr>
		</table>
		<br>
		<br> <a href="/Admin/orderList">注文一覧に戻る</a>

	</div>
</body>
</html>