package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.Message;

/**
 * Servlet implementation class RemoveMessageServlet
 */
public class RemoveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String key=request.getParameter("key");
		String id=request.getParameter("id");

		
		response.setContentType("text/json");
		PrintWriter out=response.getWriter();
		JSONObject o = Message.removeMessage(key, id);
		out.println(o);
	}


}
