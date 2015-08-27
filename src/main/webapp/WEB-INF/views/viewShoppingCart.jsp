<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート一覧</title>
<link rel="stylesheet" type="text/css" href="../css/ecHeader.css" />
</head>
<body>
	<header>
		<div id="userHeader" align="right">
			<p>こんにちはゲストさん</p>
			<p>
				<a href="/loginUser">ログイン</a>
			</p>
		</div>
		<div id="linkHeader" align="left">
			<h1 align="left">
				<a href="/serchItem/"><img src="../img/rakus.jpg" width="50"
					height="50" alt="ロゴ画像">ＥＣサイトラクス</a>
			</h1>
		</div>
			<div id="title" align="center"></div>
	</header>


	<h2 align="center">ショッピングカート一覧</h2>

    <p align="center"><c:out value="${message}"/></p>

        <table border ="1"  align="center">
            <tr>
                <th colspan="2">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr>
            
            <c:forEach var="item" items="${page.orderItemList}">
             <tr>
				<td><a href="/showItem/findById/${item.itemId}"><img src="../img/<c:out value="${item.item.imagePath}"/>.jpg" width="150"height="125" alt="商品画像"></a></td>
                <td><a href="/showItem/findById/${item.itemId}"><c:out value="${item.item.name}"/></a></td>
                <td>&yen;<fmt:formatNumber value="${item.item.price}" pattern="###,###" /></td>
                <td><c:out value="${item.quantity}"/>個</td>
                <td>
                    <form action="/deleteCartItem" method="post">
                        <input type="hidden" name="itemId" value="${item.itemId}">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
  
            </c:forEach>
            
        </table><br>

    <div  align="center"><a href="/makePayment/">決済へ</a></div>
    


</body>
</html>