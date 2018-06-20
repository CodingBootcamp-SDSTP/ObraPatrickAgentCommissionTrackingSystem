public class Agent
{
	private final String ID;
	private String firstName;
	private String lastName;
	private int age;
	private String email;

	public Agent(String id, String fn, String ln, String eml, int a){
		ID = id;
		firstName = fn;
		lastName = ln;
		email = eml;
		age = a;

	}

	public String getId(){
		return(ID);
	}

	public void setFirstName(String fn) {
		firstName = fn;
	}

	public String getFirstName() {
		return(firstName);
	}

	public void setLastName(String ln) {
		lastName = ln;
	}

	public String getLastName() {
		return(lastName);
	}

	public void setAge(int a) {
		age=a;
	}

	public int getAge() {
		return(age);
	}

	public void setEmail(String eml) {
		email=eml;
	}

	public String getEmail() {
		return(email);
	}

	public String toString() {
		String str = "ID: " + ID + ", Name: " + firstName + " " + lastName + ", Age: " + 
		age + ", Email: " + email;
		return(str);
	}

	public String getDetails() {
		return(ID + "@" + firstName + "@" + lastName + "@" + age + "@" + email);
	}
}