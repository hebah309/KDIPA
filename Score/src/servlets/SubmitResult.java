package servlets;

import java.io.IOException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SubmitResult
 */
@WebServlet("/SubmitResult")
public class SubmitResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/score";
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	   Connection conn = null;
	   Statement stmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Submit(request, response);
	}

	private void Submit(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		try{
			
			//retrieve a session 
			HttpSession session=request.getSession(false); 
			
			if(session.getAttribute("username") == null )
			{
				
				 RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
		 	     rd.forward(request, response);
		 	     return;
			}
			
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		      request.setCharacterEncoding("UTF-8");
		      String contractId = request.getParameter("flag");
		      String[] company = request.getParameterValues("companiesNames");
		      String[] criteriaName = request.getParameterValues("criteriaName");
		      String[] evaluation = request.getParameterValues("creva");
		      List<String> list2 = new ArrayList<String>(Arrays.asList(evaluation));
		      String username = session.getAttribute("username").toString();
		      String section = null;
		      String sql = "SELECT section  FROM users WHERE username = '" + username + "'";
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      if(rs.next())
		      {
		    	  section = rs.getString("section");
//		    	  System.out.println("user in section: "+section);
		      }
		     
		      for(int i =0; i<company.length; i++)
		      {
		    	  System.out.println("The company name is: "+company[i]);
		    	  if(section.equals("FIN"))
		    	  {
		    		  for(int j=0 ; j<criteriaName.length; j++)
		    		  {
		    			  System.out.println("score #"+(i+1)+" is "+list2.get(0));
				    	  list2.remove(0);
		    		  }
		    		  
		    	  }
		    	  
		      }
//		      
		         
//		 	  RequestDispatcher rd= request.getRequestDispatcher("criteriaEval.jsp");
//		 	  rd.forward(request, response);	
	
		}//End of try
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }//end finally try
	}
	
	

}
