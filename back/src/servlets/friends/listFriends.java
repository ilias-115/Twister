package servlets.friends;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class listFriends extends HttpServlet {
	@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String key = request.getParameter("key");
			
		response.setContentType("text/json");

		PrintWriter out = response.getWriter();

		out.println(services.Friends.listFriends(key));
		
	}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
	doGet(request, response);
}


}
