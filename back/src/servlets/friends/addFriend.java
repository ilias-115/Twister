package servlets.friends;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import services.Friends;
import javax.servlet.http.HttpServletRequest;

public class addFriend extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String key = request.getParameter("key");
		String id_friend =  request.getParameter("idFriend");
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
			
		out.println(services.Friends.addFriend(key, Integer.parseInt(id_friend)));
		
	}
}
