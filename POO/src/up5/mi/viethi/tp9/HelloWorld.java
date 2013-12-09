package up5.mi.viethi.tp9;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet qui renvoi une page html
public class HelloWorld extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		// récupère le flux de sortie vers le client
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head><title>Hello World</title></ head >");
		out.println("<body>");
		out.println("<font size=\"+3\">Hello World<br/></font>");
		out.println("</body>");
		out.println("</html>");
	}
	
}
