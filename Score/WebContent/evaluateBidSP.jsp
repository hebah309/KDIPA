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
%>
<div class="container">
${contract}


<form action="${pageContext.request.contextPath}/SubmitResult" method=post>
<c:forEach items="${Companies}" var="record">
<h2><c:out value="${record}" /></h2>
<input type="hidden" name="companiesNames" value="${record}">
     <c:forEach items="${criteria}" var="cr">
     <%-- <input type="hidden" name="criteriaNames" value="${cr}"> --%>
     <div class="form-group"> 
    <label for="crId"><c:out value="${cr}" /></label>
    <input type="number" class="form-control" id="crId" name="creva" min="1" max="5" required>
    <input type="hidden" name="flag" value="${contract}">
    
    </div>
    </c:forEach>
    <hr>
</c:forEach>


<c:forEach items="${criteria}" var="record">
<input type="hidden" name="criteriaName" value="${record}">
</c:forEach>


<button type="submit" class="btn btn-primary"> Submit </button>
</form>
</div>
</body>
