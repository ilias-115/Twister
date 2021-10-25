package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.User;
/**
 * Servlet de création d'utilisateur.
 *
 */
public class CreateUser extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		
		JSONObject json = services.User.createUser(login, password, prenom, nom);
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/json");

		out.println(json);
	}
	

}
