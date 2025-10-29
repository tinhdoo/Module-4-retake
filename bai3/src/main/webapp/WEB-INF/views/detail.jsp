<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Chi tiết cầu thủ</title>
</head>
<body>
<h1>Thông tin cầu thủ</h1>
<p><strong>Mã:</strong> ${player.code}</p>
<p><strong>Tên:</strong> ${player.name}</p>
<p><strong>Ngày sinh:</strong> ${player.dob}</p>
<p><strong>Kinh nghiệm:</strong> ${player.experience}</p>
<p><strong>Vị trí:</strong> ${player.position}</p>
<img src="${player.image}" width="200" alt="Ảnh cầu thủ">
<br><br>
<a href="/players">⬅ Quay lại danh sách</a>
</body>
</html>
