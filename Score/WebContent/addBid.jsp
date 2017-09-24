<%@ include file = "Admin.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
<%ResultSet resultset =null;%>

<%
    try{

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection connection = 
         DriverManager.getConnection
            ("jdbc:mysql://localhost/score?user=root&password=password");

       Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("select * from companies") ;
%>

<div class="container">

	 <form action="${pageContext.request.contextPath}/AddBid" method="post">
	 <div class="form-group">
      <label for="bid#">: رقم الممارسة</label>
      <input type="text" class="form-control" id="bid#" name="bidID" required>
    </div>
    
    <div class="form-group">
      <label for="cmpny#">: عدد الممارسين</label>
      <input type="number" class="form-control" id="cmpny#" name="bidTotal" required>
    </div>
    
 <label for="sel1"> : الرجاء اختيار الشركات المساهمة</label>

    <select multiple class="form-control" id="sel1" name="id">
        <%  int idCompany=0;
        while(resultset.next()){ 
        	idCompany++;%>
            <option ><%= resultset.getString(2)%></option>
        <% } %>
        </select>
        
         <label for="sel2">: الموظف المسؤول من إدارة الشؤون المالية</label>

           <select  class="form-control" id="sel2" name="FINuser">
           <%  
           resultset =statement.executeQuery("select EmpName from users where section = 'FIN'");
           while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
          <% } %>
          </select>
        
          <label for="sel2">: الموظف المسؤول من الجهة الطالبة</label>

           <select  class="form-control" id="sel2" name="REQuser">
           <%  
           resultset =statement.executeQuery("select EmpName from users where section = 'request'");
           while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
          <% } %>
          </select>
        
        <%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
        </br>
    ${message}
    </br>
    <button type="submit" class="btn btn-primary"> Submit </button>
  </form>
	
</div>
