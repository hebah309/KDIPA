<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "Admin.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*" import="model.*"
    import="java.util.ArrayList"
    import="java.util.List"  
%> 
<body>
<% 
List<Evaluation> listEval = new ArrayList<Evaluation>();
Evaluation evalObj = new Evaluation();
List <String> list = new ArrayList<String>();

request.setAttribute("criteriaName22", request.getAttribute("criteriaName"));
%>


<div class="container">
${contract}

<input type="hidden" name="criteriaName" value="${criteria}"> 
<input type="hidden" name="flag" value="${contract}">

<c:forEach items="${Companies}" var="record">
 <h2>اسم الشركه :<a href="${pageContext.request.contextPath}/SubmitResult">  : <c:out value="${record}" /></a></h2>
 <input type="hidden" name="companiesNames" value="${record}"> 
</c:forEach>

</div>
</body>
