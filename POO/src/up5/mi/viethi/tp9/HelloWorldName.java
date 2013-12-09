package up5.mi.viethi.tp9;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldName extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		// récupère le flux de sortie vers le client

		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		PrintWriter out = res.getWriter();

		out.println("<html>");
		out.println("<head><title>Hello World Name</title></ head >");
		out.println("<body>");
		if (nom == null || prenom == null) {
			out.println("Nom et prénom inconnu");
		} else {
			out.println("<font size=\"+3\">Bonjour " + prenom + " " + nom
					+ " !<br/></font>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
