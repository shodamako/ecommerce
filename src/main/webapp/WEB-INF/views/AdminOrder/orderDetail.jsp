<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
<!--
	function check() {
		document.getElementById('notice-input-text-1').style.display = "block";

		return false;

	}

	-->
</script>
<title>注文詳細画面</title>


<link rel="stylesheet" type="text/css" href="../css/adminHeader.css" />

</head>
<body>



	<header>
	<div id="userHeader" align="right">
		<p>
			こんにちは管理者[<c:out value="${page.loginName }" />]さん
		</p>
		<p>
			<a href="/Admin/logout">ログアウト</a>
		</p>
	</div>
	<div id="linkHeader" align="left">
		<h1 align="left">
			<a href="/Admin/showMenu"><img src="../../img/rakus.jpg"
				width="50" height="50" alt="ロゴ画像"></a>
		</h1>
	</div>
	<div id="title" align="center"></div>
	</header>

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
			<c:forEach var="child"
				items="${ShowOrderDetailPage.orderItemList}">
				<tr>
					<td><c:out value="${child.item.name}" /></td>
					<td><fmt:formatNumber value="${child.item.price}" pattern="###,###" />円</td>
					<td><c:out value="${child.quantity}" /></td>
					<td><fmt:formatNumber value="${ShowOrderDetailPage.totalPrice}"
							pattern="###,###" />円</td>
				</tr>
			</c:forEach>
		</table>
		<br>


		<table border="1">
			<tr>
				<th nowrap>小計</th>
				<td><fmt:formatNumber value="${ShowOrderDetailPage.totalPrice}"
						pattern="###,###" />円</td>
			</tr>
			<tr>
				<th nowrap>税</th>
				<td><fmt:formatNumber value="${ShowOrderDetailPage.totalPrice * 0.08}"
						pattern="###,###" />円</td>
			</tr>
			<tr>
				<th nowrap>支払い方法</th>
				<td><c:out value="${ShowOrderDetailPage.payment}" /></td>
			</tr>
			<tr>
				<th nowrap>送料一律</th>
				<td>500円</td>
			</tr>
			<tr>
				<th nowrap>総計</th>
				<td><fmt:formatNumber value="${ShowOrderDetailPage.totalPrice * 1.08 + 500}"
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
				<td>
					<form:form modelAttribute="orderStatusForm" action="/Admin/ShowOrderDetail/UpdateStatus" >
						<form:select path="status" items="${statusMap }"/>
						 <input type="hidden" name="id" value="${ShowOrderDetailPage.id}">
						  <input class="btn" type="submit" value="更新">
					</form:form>
				</td>
			</tr>
		</table>
		<br>
		<p style="color:red"><c:out value="${message}"/></p>
		<br> <a href="/Admin/orderList">注文一覧に戻る</a>

	</div>
</body>
</html>