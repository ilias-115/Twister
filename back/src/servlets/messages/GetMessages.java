package servlets.messages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMessages extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String id_friend =  request.getParameter("idFriend");
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
			
		out.println(services.Messages.getMessages(key, Integer.parseInt(id_friend)));
		
	}
}
