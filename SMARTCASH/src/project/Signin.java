package project;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * Servlet implementation class Signin
 */
public class Signin extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		   try {
			   res.setContentType("text/html");
			   PrintWriter out =res.getWriter();
			   String email=req.getParameter("email");
			   String pswd=req.getParameter("pswd");
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			   Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			   PreparedStatement pstmt=con.prepareStatement("select * from signup where email=? and pswd=?");
			   pstmt.setString(1,email);
			   pstmt.setString(2, pswd);
			   ResultSet rs=pstmt.executeQuery();
			   if(email.equals("admin") & pswd.equals("Admin@123")) {
			    HttpSession session=req.getSession();
			    session.setAttribute("email",email);
			    session.setAttribute("pswd",pswd);
			    res.sendRedirect("Dashboard.html");//admin page
			   }
			   else if(rs.next()) {
			   //else if(!username.equals("admin") & !password.equals("Admin@123")) {
			    res.sendRedirect("Dashboard.html");
			   }
			   else {
			    out.println("Invalid details...login again");
			    req.getRequestDispatcher("signin.html").include(req,res);
			   }
			    }
			   catch(Exception e) {
			    e.printStackTrace();
			   }
			  
}

}
