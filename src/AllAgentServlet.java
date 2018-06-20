import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class AllAgentServlet extends HttpServlet
{
	AgentCollection agents;

	public void init() throws ServletException {
		AgentDataStore ads = new AgentDataStore();
		agents = ads.getAgentCollection();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/xml");
		PrintWriter out = res.getWriter();
		out.println(exportToXML());
	}

	public String exportToXML() {
		StringBuilder sb = new StringBuilder("<agent>");
		ArrayList<Agent> agentReps = agents.getAllAgents();
		System.out.println("testing only hehe");
		for(Agent a : agentReps) {
			sb.append("<agent><id>");
			sb.append(a.getId());
			sb.append("</id><firstname>");
			sb.append(a.getFirstName());
			sb.append("</firstname><lastname>");
			sb.append(a.getLastName());
			sb.append("</lastname><age>");
			sb.append(a.getAge());
			sb.append("</age><email>");
			sb.append(a.getEmail());
			sb.append("</email></agent>");
		}
		sb.append("</agent>");
		return(sb.toString());
	}

	public void destroy() {
		agents = null;
	}
}
