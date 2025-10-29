<%--
  Created by IntelliJ IDEA.
  User: Tịnh
  Date: 10/29/2025
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Thêm mới</title>
</head>
<body>
<h2>Thêm cầu thủ mới</h2>
<form:form action="/add" modelAttribute="player" method="post">
    <p>Mã cầu thủ:</p>
    <form:input path="code"/> <br>

    <p>Tên cầu thủ:</p>
    <form:input path="name"/> <br>

    <p>Ngày sinh:</p>
    <form:input path="dob"/> <br>

    <p>Kinh nghiệm:</p>
    <form:input path="experience"/> <br>

    <p>Vị trí:</p>
    <form:input path="position"/> <br>

    <p>Ảnh:</p>
    <form:input path="image"/>
    <br>
    <form:button>Lưu</form:button>
</form:form>

</body>
</html>
