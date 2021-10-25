package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet de connexion.
 *
 */
public class Login extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		
		JSONObject json = null;
		try {
			json = services.User.login(login, password);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/json");
		out.println(json);
	}
	

}
