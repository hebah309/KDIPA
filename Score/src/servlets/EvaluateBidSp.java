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



/**
 * Servlet implementation class EvaluateBidSp
 */
@WebServlet("/EvaluateBidSp")
public class EvaluateBidSp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/score";
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "password";
	   Connection conn = null;
	   Statement stmt = null;
	   Connection conn2 = null;
	   Statement stmt2 = null;
	   Connection conn3 = null;
	   Statement stmt3 = null;
 /**
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluateBidSp() {
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
		String contractId = request.getParameter("contractId");
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt = (Statement) conn.createStatement();
		      conn2 = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt2 = (Statement) conn.createStatement();
		      conn3 = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		      stmt3 = (Statement) conn.createStatement();
		    //retrieve a session 
				HttpSession session=request.getSession(); 
				if(session.getAttribute("username") == null )
				{
					
					 RequestDispatcher rd= request.getRequestDispatcher("login.jsp");
			 	     rd.forward(request, response);
			 	     return;
				}
				String usernameSession = session.getAttribute("username").toString();

		    //select statement to get all the companies that involve in this contract
				String sql = "SELECT companies.name as companyName FROM  score.companies left join score.BidDetails ON companies.id=BidDetails.companyid where BidDetails.bidid = '"+contractId+"'" ;
			    //check the user in which section to retrieve the criteria 
				String sql2 = "SELECT section FROM users WHERE username = '" + usernameSession + "'";

			    String section = null;
			    ResultSet rs = stmt.executeQuery(sql);
		        ResultSet rs2 = stmt2.executeQuery(sql2);
		        
		       if(rs2.next())
		       {
		    	   section = rs2.getString("section");
		       }
		       //retrieve specific criteria 
		       String sql3 = "SELECT criteria.name AS criteriaName FROM  score.criteria where criteria.section = '"+section+"' ";
//		       rs2 = stmt2.executeQuery(sql3);
		       ResultSet rs3 = stmt3.executeQuery(sql3);
		       String companyName = "";
	    	   List<String> criteria = new ArrayList<String>();
	    	   List<String> Companies = new ArrayList<String>();
	    	   String criteriaName = null;
	    	   while(rs3.next())
	    	   {
	    		   criteriaName = rs3.getString("criteriaName");
	    		   criteria.add(criteriaName);
	    	   }
		       while (rs.next())
			       {
		    	   companyName = rs.getString("companyName");
		    	   Companies.add(companyName);
			            
			       }
		         request.setAttribute("contract", contractId);
		         request.setAttribute("Companies",Companies);
		         request.setAttribute("criteria",criteria);
		         //+++++++++++++++++++++++++++++++++++++++++ IT WAS evaluateBidSP.jsp
		 	     RequestDispatcher rd= request.getRequestDispatcher("evaluateBidSP.jsp");
		 	     rd.forward(request, response);		       
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
