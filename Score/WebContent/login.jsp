<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">

<title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
            <h4>Welcome back.</h4>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            <input type="text" name="username" id="userName" class="form-control input-sm chat-input" placeholder="username" required/>
            </br>
            <input type="password" name="password" id="password" class="form-control input-sm chat-input" placeholder="password" required/>
            </br>
            ${message}
            </br>
            <div class="wrapper">
            <span class="group-btn"> 
            <button type="submit" class="btn btn-primary"> Login <i class="fa fa-sign-in"></i>  </button>
                
            </span>
            </form>
            </div>
            </div>
        
        </div>
    </div>
</div>
</body>
</html>