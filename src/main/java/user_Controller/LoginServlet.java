package user_Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userDao.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		boolean b = false;
		
		resp.setContentType("text/html");
		
		UserDao dao = new UserDao();
		try
		{
			b = dao.Validate(email,Integer.parseInt(pass));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(b==true)
		{
			RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
			rd.forward(req, resp);
		}
		else
		{
			PrintWriter pw = resp.getWriter();
			pw.print("incorrect username or password.....");
			
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
		}
	}
}
