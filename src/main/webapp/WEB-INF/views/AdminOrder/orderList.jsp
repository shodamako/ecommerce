<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文一覧画面</title>


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
					<td><Div Align="right"><a href="/Admin/ShowOrderDetail/${order.id}">
							<c:out value="${order.orderNumber}" /></a></Div></td>
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