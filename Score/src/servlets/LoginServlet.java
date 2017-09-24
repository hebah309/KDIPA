package servlets;

import java.io.IOException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		//store the input in variables and send them to a method called authentication
		String  username = request.getParameter("username");
		String  password = request.getParameter("password");
		authentication(username, password, request, response);
	}

	private void authentication(String username, String password, HttpServletRequest request, HttpServletResponse response)
	{
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		   //select statement to check if the entries match the database data or not
		       String sql = "SELECT username, type, section  FROM users WHERE username = '" + username + "' and password ='" + password+"'";
		       
		       ResultSet rs = stmt.executeQuery(sql);
//		       int size = 0;
//			      while(rs.next())
//			      {
//			    	  size++;
//			      }
		       //the inputs match the database data
			      if (rs.next())
			       {
			         rs = stmt.executeQuery(sql);
			         String type = null;
			         String section = null;
			         //get the type of the employee and he works in which section
			         while(rs.next())
				      {
			        	 type = rs.getString("type");
			        	 section = rs.getString("section");
			        	 
				      }
			         
			         if(type.equals("admin") && section.equals("FIN"))
			         {
			        	 
			        	 HttpSession session=request.getSession();  
			             session.setAttribute("username",username);
			             RequestDispatcher rd= request.getRequestDispatcher("Admin.jsp");
				 	     rd.forward(request, response);
			         }
			         else if (type.equals("user") && section.equals("FIN"))
			         {
			        	 RequestDispatcher rd= request.getRequestDispatcher("userFin.jsp");
				 	     rd.forward(request, response);
			         }
			         else if (type.equals("user") && section.equals("request"))
			         {
			        	 RequestDispatcher rd= request.getRequestDispatcher("requestUser.jsp");
				 	     rd.forward(request, response);
			         }
			         
			       }//end of outer if
			      //the input does not match the database data
			      else
			      {
//			    	  System.out.println("NOT Pass");
			    	     
			    	  request.setAttribute("message", "إسم المستخدم أو كلمة المرور خاطئة"); // Will be available as ${message}
			    	  request.getRequestDispatcher("login.jsp").forward(request,response); 
			      }
		       
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
