package project;

import javax.servlet.http.HttpServlet;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  try {
		   response.setContentType("text/html");
		   PrintWriter out=response.getWriter();  
		  String fname=request.getParameter("fname");
		  String lname=request.getParameter("lname");
		  String email=request.getParameter("email");
		  String pswd=request.getParameter("pswd");
		  String address=request.getParameter("address");
		  String city=request.getParameter("city");
		  String state=request.getParameter("state");
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  PreparedStatement pstmt=con.prepareStatement("insert into signup values(?,?,?,?,?,?,?)");
		  pstmt.setString(1,fname);
		  pstmt.setString(2, lname);
		  pstmt.setString(3, email);
		  pstmt.setString(4, pswd);
		  pstmt.setString(5, address);
		  pstmt.setString(6, city);
		  pstmt.setString(7, state);
		  int i=pstmt.executeUpdate();
		  if(i>0) 
		  {
		   response.sendRedirect("signin.html");
		  }
		  else {
		   out.println("Details are invalid..login again");
		   request.getRequestDispatcher("signup.html").include(request,response);
		  }
		  }
		  catch(Exception e) {
		   e.printStackTrace();
		  }
		 }
}
