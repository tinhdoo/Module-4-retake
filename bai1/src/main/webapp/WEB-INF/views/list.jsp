<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách cầu thủ</title>
</head>
<body>
<h1>Danh sách cầu thủ</h1>
<table border="1" cellpadding="6" cellspacing="0">
    <tr>
        <th>Mã</th>
        <th>Họ tên</th>
        <th>Vị trí</th>
        <th>Chi tiết</th>
        <th>Xóa</th>
    </tr>
    <c:forEach var="p" items="${players}">
        <tr>
            <td>${p.code}</td>
            <td>${p.name}</td>
            <td>${p.position}</td>
            <td><a href="players/${p.code}">Xem</a></td>
            <td><a href="players/delete/${p.code}" onclick="return confirm('Xóa cầu thủ này?')">Xóa</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
