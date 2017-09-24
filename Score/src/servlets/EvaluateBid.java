package servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import model.BidDetails;


/**
 * Servlet implementation class EvaluateBid
 */
@WebServlet("/EvaluateBid")
public class EvaluateBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/score";
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	   Connection conn = null;
	   Statement stmt = null;
	   Statement stmt2 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluateBid() {
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
		// TODO Auto-generated method stub
//		doGet(request, response);
		evaluateBid(request, response);
		 
	}
	
	private void evaluateBid(HttpServletRequest request, HttpServletResponse response)
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
		String usernameSession = session.getAttribute("username").toString();
		 //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
	      stmt = (Statement) conn.createStatement();
	      stmt2 = (Statement) conn.createStatement();
	      //select statement to retrieve the id of the user
	       String sql1 = "SELECT id  FROM users WHERE username = '" + usernameSession + "'";
	       ResultSet rs = stmt.executeQuery(sql1);
	       int userid=-1;
	       while(rs.next())
	       {
	    	    userid = rs.getInt("id");

	       }
	       System.out.println(userid);
	       //select statement to retrieve all the contract that assign to this user
	       String retriveBid = "SELECT DISTINCT(BidDetails.bidid) AS contractId  FROM score.BidDetails LEFT JOIN score.users ON BidDetails.assignToFin=users.id where  BidDetails.assignToFin ="+userid;
	       rs = stmt.executeQuery(retriveBid);
	       //there are no assign contract to this user
	       if(!rs.next())
	       {
	    	   request.setAttribute("evaluate", "لا توجد ممارسات"); // Will be available as ${message}
		       request.getRequestDispatcher("evaluateBid.jsp").forward(request,response);

	       }
	       //there are assign contract to this user
	       else
	       {
	    	   String contractId=null;
	    	   List<BidDetails> list = new ArrayList<BidDetails>();
	    	   rs = stmt.executeQuery(retriveBid);
	    	   //retrieve the contract details and store them in list to print them in evaluateBid.jsp
	    	   while(rs.next())
	    	   {
	    		   contractId = rs.getString("contractId");
	    		   BidDetails bidDetail = new BidDetails();
	    		   bidDetail.setBidId(contractId);
	    		   list.add(bidDetail);
	    	   }
	    	     request.setAttribute("BidId2",contractId);
	    	     request.setAttribute("list",list);
		 	     RequestDispatcher rd= request.getRequestDispatcher("evaluateBid.jsp");
		 	     rd.forward(request, response);
	       }
	   
		}//End Of Try
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
