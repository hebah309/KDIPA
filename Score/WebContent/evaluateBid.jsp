<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "Admin.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%> 
    <style>
th,body {
    text-align: center
}

</style>
<body>
<div class="container">
${evaluate}
<c:if test="${not empty list}">
	<h2>ممارسات قيد التقييم</h2>
	<c:forEach items="${list}" var="record">
	
	<p >رقم الممارسة<a href="${pageContext.request.contextPath}/EvaluateBidSp?contractId=${record.bidId}">  : <c:out value="${record.bidId}" /></a></p>
	
	</c:forEach>
    
</c:if>


</div>
</body>
