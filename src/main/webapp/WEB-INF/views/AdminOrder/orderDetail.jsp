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
			<p>こんにちは管理者[<c:out value="${page.loginName }"/>]さん</p>
			<p>
				<a href="administerLogin.html">ログアウト</a>
			</p>
		</div>
		<div id="linkHeader" align="left">
			<h1 align ="left"><a href="/Admin/showMenu"><img src="../img/rakus.jpg" width="50"
				height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1>
			<div id="title" align="center"></div>
	</header>

	<div align="center">
		<h1>注文詳細画面</h1>
		<table border="1">
			<tr>
				<th nowrap>注文NO</th>
				<td><c:out value="${ShowOrderDetailPage.no}" /></td>
			</tr>
			<tr>
				<th nowrap>名前</th>
				<td><c:out value="${ShowOrderDetailPage.name}" /></td>
			</tr>
			<tr>
				<th nowrap>アドレス</th>
				<td><c:out value="${ShowOrderDetailPage.email}" /></td>
			</tr>
			<tr>
				<th nowrap>住所</th>
				<td><c:out value="${ShowOrderDetailPage.address}" /></td>
			</tr>
			<tr>
				<th nowrap>TEL</th>
				<td><c:out value="${ShowOrderDetailPage.telephone}" /></td>
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
				items="${ShowOrderDetailPage.showOrderDetailChildPageList}">
				<tr>
					<td><c:out value="${child.name}" /></td>
					<td><fmt:formatNumber value="${child.price}" pattern="###,###" />円</td>
					<td>×</td>
					<td><c:out value="${child.count}" /></td>
					<td><fmt:formatNumber value="${child.totalPrice}"
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
				<td><fmt:formatNumber value="${ShowOrderDetailPage.tax}"
						pattern="###,###" />円</td>
			</tr>
			<tr>
				<th nowrap>支払い方法</th>
				<td><c:out value="${ShowOrderDetailPage.paymentMethod}" /></td>
			</tr>
			<tr>
				<th nowrap>送料一律</th>
				<td><fmt:formatNumber value="${SHowOrderDetailPage.postage}"
						pattern="###,###" />円</td>
			</tr>
			<tr>
				<th nowrap>総計</th>
				<td><fmt:formatNumber value="${ShowOrderDetailPage.grandTotal}"
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
					<form action="/changeorderstatus/update" method="post">
						<select name="status">
							<option value="1">未入金</option>
							<option value="2" selected>入金済み</option>
							<option value="3">発送済み</option>
							<option value="9">キャンセル</option>
						</select> <input type="hidden" name="id"
							value="${ShowOrderDetailPage.id}"> <input class="btn"
							type="submit" value="更新">
					</form>
				</td>
			</tr>
		</table>
		<br>
		<p style="color:red"><c:out value="${message}"/></p>
		<br> <a href="/AdminOrder/orderList">注文一覧に戻る</a>

	</div>
</body>
</html>