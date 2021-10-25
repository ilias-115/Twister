package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.Likes;

/**
 * Servlet implementation class RemoveLikeServlet
 */
public class RemoveLikeServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String login=request.getParameter("login");
		String id_message=request.getParameter("id_message");

		response.setContentType("text/json");
		PrintWriter out=response.getWriter();
		JSONObject o = Likes.removeLike(login, id_message);
		out.println(o);
	}


}
