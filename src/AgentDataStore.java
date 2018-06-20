import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.sql.*;

public class AgentDataStore
{
	private AgentCollection agents;

	public AgentDataStore() {
		agents = new AgentCollection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/actsdb?user=patrick&password=pats&serverTimezone=UTC&useSSL=false");
			readFromDB(conn);
			}
			catch (Exception e) {
				e.printStackTrace();
		}
	}

	public AgentCollection getAgentCollection() {
		return(agents);
	}

	public Agent getPersonById(String id) {
		return(agents.getAgentById(id));
	}

	public ArrayList<Object> search(String s) {
		ArrayList<Object> ao = new ArrayList<Object>();
		ArrayList<Agent> as = agents.search(s);
		ao.addAll(as);
		return(ao);
	}

	public boolean readFromDB(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		boolean v = false;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM agent;");
			while(rs.next()) {
				String[] str = {
					rs.getString("agentid"),
					rs.getString("firstName"),
					rs.getString("lastName"),
					rs.getString("email"),
					Integer.toString(rs.getInt("age")),
					"agents"
				};
				createObject(str);
			}
			v = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try { if (rs != null) rs.close(); } catch (Exception e) {};
			try { if (rs != null) stmt.close(); } catch (Exception e) {};
		}
		return(v);
	}
	public void createObject(String... details) {
		int len = details.length - 1;
		String d = details[len];
		if("agents".equals(d)) {
			Agent a = new Agent(details[0], details[1], details[2], details[3], Integer.parseInt(details[4]));
			agents.addAgent(a); 
		}
	}

	public boolean writeToFile(String filename) {
		boolean f = false;
		try {
			FileWriter fWriter = new FileWriter(filename);
			BufferedWriter fbw = new BufferedWriter(fWriter);
			fbw.write(getCollectionContent());
			fbw.close();
			f = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return(f);
	}

	public String getCollectionContent() {
		ArrayList<Agent> a = agents.getAllAgents();
		StringBuffer sb = new StringBuffer("");
		for(int i =0; i<a.size(); i++) {
			Agent agent = a.get(i);
			sb.append(agent.getDetails());
			if(i<a.size()-1) {
				sb.append("\n");
			}
		}
		return(sb.toString());
	}
}
