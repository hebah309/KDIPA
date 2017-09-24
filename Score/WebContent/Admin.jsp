<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

    
<!DOCTYPE html >
<html dir="rtl" lang="ar">
<head>
 
<meta http-equiv="Content-Type" content="text/html; charset='UTF-8'">
<title>KDIPA System</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">KDIPA Scoring System</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="addCompany.jsp">اضافة شركة</a></li>
      <li><a href="addBid.jsp">إضافة ممارسة</a></li>
      <li><a href="#">نتائج ممارسة معينة</a></li>
      <li><a href="#">اعتماد ممارسة</a></li>
      <li><a href="${pageContext.request.contextPath}/EvaluateBid">تقييم ممارسة</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="${pageContext.request.contextPath}/SignOut"> خروج <span class="glyphicon glyphicon-log-in"></span></a></li>
    </ul>
  </div>
</nav>
</body>
</html>