import java.util.ArrayList;

class AgentCollection
{
	ArrayList<Agent> agents;

	public AgentCollection() {
		agents = new ArrayList<Agent>();
	}

	public void addAgent(Agent agent) {
		agents.add(agent);
	}

	public void removeAgent(Agent agent) {
		agents.remove(agent);
	}

	public ArrayList<Agent> getAllAgents() {
		return(agents);
	}

	public Agent getAgentByIndex(int n) {
		return(agents.get(n));
	}

	public int getAgentCount() {
		return(agents.size());
	}

	public Agent getAgentById(String id) {
		Agent agent = null;
		for(int i = 0; i<getAgentCount(); i++) {
			if(getAgentByIndex(i).getId().equalsIgnoreCase(id)) {
				agent = getAgentByIndex(i);
			}
		}
		return(agent);
	}

	public ArrayList<Agent> search(String s) {
		Agent a = null;
		ArrayList<Agent> al = new ArrayList<Agent>();
		String str = s.toLowerCase();
		for(int i = 0; i<getAgentCount(); i++) {
			a = getAgentByIndex(i);
			if (matches(a, str)) {
				al.add(a);
			}
		}
		return(al);
	}

	public boolean matches(Agent a, String str) {
		String id = a.getId().toLowerCase();
		String firstName = a.getFirstName().toLowerCase();
		String lastName = a.getLastName().toLowerCase();
		int age = a.getAge();
		String email = a.getEmail().toLowerCase();
		String ag = Integer.toString(age);
		if(id.contains(str) || firstName.contains(str) || lastName.contains(str) || ag.contains(str) || email.contains(str)) {
			return(true);
		}
		return(false);
	}
}
