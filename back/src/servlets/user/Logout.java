package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet de connexion.
 *
 */
public class Logout extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String key = request.getParameter("key");
		
		JSONObject json = services.User.logout(key);
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/json");

		out.println(json);
	}

}
