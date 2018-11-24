package entity;

public class Client {

	private int clientId;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String phone;
	
	public Client() {}
	
	public Client(int clientId, String firstName, String lastName, int age, String email, String phone) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}

	public int getClientId() {
		return clientId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
