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
 * Servlet implementation class AddCompany
 */
@WebServlet("/AddCompany")
public class AddCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  // JDBC driver name and database URL
	   static final String unicode= "?characterEncoding=utf8";
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/score?useUnicode=yes&characterEncoding=UTF-8";
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	   Connection conn = null;
	   Statement stmt = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCompany() {
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
		addCompany(request, response);

		
	}
	
	private void addCompany(HttpServletRequest request, HttpServletResponse response)
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
			request.setCharacterEncoding("UTF-8");
			String companyName = request.getParameter("companyName");
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		      //select statement to check if the company exist or not 
		       String sql = "SELECT *  FROM companies WHERE name = '" + companyName + "'";
		       ResultSet rs = stmt.executeQuery(sql);
		       //the company already exist
		       if (rs.next())
			       {
		    	   request.setCharacterEncoding("UTF-8");
		    	   request.setAttribute("addComp", "الرجاء ادخال اسم اخر"); // Will be available as ${message}
			       request.getRequestDispatcher("/addCompany.jsp").forward(request,response);    
			       
			       }
		       //else its not exist
		       else
			       {
		    	      sql = "INSERT INTO companies (name)" +
		                   "VALUES ('"+companyName+"')";
		              stmt.executeUpdate(sql);
		              request.setCharacterEncoding("UTF-8");
		              request.setAttribute("addComp", "تم إضافة الشركة"); // Will be available as ${message}
				      request.getRequestDispatcher("/addCompany.jsp").forward(request,response); 
 
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
