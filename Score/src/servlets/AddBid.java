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
 * Servlet implementation class AddBid
 */
@WebServlet("/AddBid")
public class AddBid extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	  // JDBC driver name and database URL
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
    public AddBid() {
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
		addBid(request, response);
	}
	
	private void addBid(HttpServletRequest request, HttpServletResponse response)
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
			//take all the input and store them in variables 
			String bidId = request.getParameter("bidID");
			String FINuser = request.getParameter("FINuser");
			String REQuser = request.getParameter("REQuser");
//			int BidTotal = Integer.parseInt(request.getParameter("bidTotal"));
			//it an array because the user can select multiple companies
			String[] idCompany = request.getParameterValues("id");
			String sql2= null;
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		      //STEP 3: Open a connection
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		   //select statement to check if the contract already registered in the database
		       String sql = "SELECT *  FROM bid WHERE contractId = '" + bidId + "'";
		      
		       ResultSet rs = stmt.executeQuery(sql);
		       //check if the contract exist
		       if (rs.next())
			       {
		    	   request.setAttribute("message", "الممارسة مضافة من قبل!"); // Will be available as ${message}
			       request.getRequestDispatcher("addCompany.jsp").forward(request,response);    
			       }
		       else
			       {
		    	   //will execute this part if the contract not exist in the database
		    	   int company = -1;
		    	   int FINID=-1;
		    	   int REQID=-1;
//		    	   String sql3 = null;
		    	   //select statement to retrieve the id of the employee that has been selected to this contract
		    	   String sqlAddFin="SELECT id FROM users WHERE EmpName='"+FINuser+"'";
		    	   String sqlAddReq="SELECT id FROM users WHERE EmpName='"+REQuser+"'";
		    	   rs = stmt.executeQuery(sqlAddFin);
		    	   while(rs.next())
		    	   { FINID = rs.getInt("id");}
		    	   rs = stmt.executeQuery(sqlAddReq);
		    	   while(rs.next())
		    	   {REQID = rs.getInt("id");}
		    	   //loop depend on # of the companies that has been selected
		    	   for(int i=0; i<idCompany.length; i++)
					{
							//select statement to retrieve the company id
							 sql2 = "SELECT id FROM companies WHERE name='"+idCompany[i]+"'";
							 
							  rs = stmt.executeQuery(sql2);
							  System.out.println("Selected company" +idCompany[i]);
							 if(rs.next())
							 {
								 company=rs.getInt("id");
								 
								 sql = "INSERT INTO BidDetails (bidId,companyId,assignToFin,assignToReq)" +
						                   "VALUES ('"+bidId+"',"+company+",'"+FINID+"','"+REQID+"')";
						              stmt.executeUpdate(sql);
							 }
					 
					}
		    	      
		              request.setCharacterEncoding("UTF-8");
		              request.setAttribute("message", "تم الإضافة بنجاح"); // Will be available as ${message}
				      request.getRequestDispatcher("addCompany.jsp").forward(request,response); 
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
