import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class AllAgentServlet extends HttpServlet
{
	

	public void init() throws ServletException {
		
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/xml");
		StringBuilder sb = new StringBuilder("<>");
		sb.append("<>");
		PrintWriter out = res.getWriter();
		out.println(sb.toString());
		out.close();
	}

	public void destroy() {

	}
}
