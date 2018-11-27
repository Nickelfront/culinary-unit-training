package entity;

public class Mentor {

	private int mentorId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private double salary;

	public Mentor() {}
	
	public Mentor(int mentorId, String firstName, String lastName, String phone, String email, double salary) {
		this.mentorId = mentorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getMentorId() {
		return mentorId;
	}
	
}
