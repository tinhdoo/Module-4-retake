<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Player Details</title>
</head>
<body>
<h1>Player Details</h1>
<p><strong>Mã cầu thủ:</strong> ${player.code}</p>
<p><strong>Họ và tên:</strong> ${player.name}</p>
<p><strong>Ngày sinh:</strong> ${player.dob}</p>
<p><strong>Kinh nghiệm:</strong> ${player.experience}</p>
<p><strong>Vị trí:</strong> ${player.position}</p>
<p><strong>Ảnh đại diện:</strong> <img src="${player.image}" alt="Ảnh đại diện" width="200"></p>
</body>
</html>
