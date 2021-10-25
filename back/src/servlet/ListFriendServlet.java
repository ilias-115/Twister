package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.Friends;

/**
 * Servlet implementation class ListMessageServlet
 */
public class ListFriendServlet extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login=request.getParameter("login");
		

		response.setContentType("text/json");
		PrintWriter out=response.getWriter();
		JSONObject o = Friends.listFriend(login);
		out.println(o);
		
	}

}
