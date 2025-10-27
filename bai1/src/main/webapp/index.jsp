<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Player Details</title>
</head>
<body>
<h1>Player Details</h1>
<p><strong>Mã cầu thủ:</strong> ${player.maCauThu}</p>
<p><strong>Họ và tên:</strong> ${player.hoVaTen}</p>
<p><strong>Ngày sinh:</strong> ${player.ngaySinh}</p>
<p><strong>Kinh nghiệm:</strong> ${player.kinhNghiem}</p>
<p><strong>Vị trí:</strong> ${player.viTri}</p>
<p><strong>Ảnh đại diện:</strong> <img src="${player.anhDaiDien}" alt="Ảnh đại diện" width="200"></p>
</body>
</html>
