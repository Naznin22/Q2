package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String email=request.getParameter("email");
		String password = request.getParameter("password");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_showroom","root","");
			System.out.println("Connected!");
			Statement stmt = con.createStatement();
			
			String sql = "SELECT * from users WHERE email='%s' AND password='%s'";
		    sql=String.format(sql, email, password);
		    
		    
		    ResultSet rs=null;
			
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
							
			if(matcher.matches()) {
				rs = stmt.executeQuery(sql);
			}else {
				out.print("Not an email address!");
			}
		     if(rs.next()){
		      	   
	             //Retrieve by column name
	             email = rs.getString("email");
	             password = rs.getString("password");
	             request.getRequestDispatcher("home.jsp").include(request, response);

	             out.close();
	             
	            
	             //response.sendRedirect("/MyProject");
	             
	            
	          }else {
	        	  
	        	  out.print("Incorrect email address or password!");  
	              //request.getRequestDispatcher("login.jsp").include(request, response); 
	  	        
	          }
		     out.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
