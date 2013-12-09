package up5.mi.viethi.tp9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet prenant en entrée un tableau d'entiers et rendant le tableau trié. 
public class TriServlets extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		// récupère le flux de sortie vers le client
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>TriServlets</title></ head >");
		out.println("<body>");
		out.println("<font size=\"+3\">Bienvenue sur la servlets de tri<br/></font>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
		int[] tab = null;
		try {
			tab = (int[]) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Arrays.sort(tab);
		ObjectOutputStream oos = new ObjectOutputStream(resp.getOutputStream());
		oos.writeObject(tab);
		
		ois.close(); oos.close();
	}
}
