<%@ include file = "Admin.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>

  
  
  
<div class="container">
 <form action="${pageContext.request.contextPath}/AddCompany" method="post">
    <div class="form-group">
      <label for="cmpny"> إسم الشركة :</label>
      <input type="text" name="companyName" class="form-control" id="cmpny" required>
    </div>
    </br>
     ${addComp}
    </br>
    <button type="submit" class="btn btn-primary"> تسجيل </button>
  </form>
</div>
