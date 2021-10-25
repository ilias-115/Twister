package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.Auth;

/**
 * Servlet implementation class AuthServlet
 */
public class LoginServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String login=request.getParameter("login"); 
		String password=request.getParameter("password");
//		int root=Integer.parseInt(request.getParameter("root"));
		
		JSONObject j= Auth.connexion(login, password,0);
		response.setContentType("text/json");
		PrintWriter out=response.getWriter(); //Affiche la réponse?
		out.println(j);
		
	}

	
	
}
