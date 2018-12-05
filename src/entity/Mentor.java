package entity;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mentor extends Base{
        static private String TB = "mentors";
    
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
        
        public void setMentorId(int id) {
		mentorId = id;
	}
        
	public int getMentorId() {
		return mentorId;
	}

    @Override
    protected void configure() {
        tableName = TB;
        
        fields.put("mentorId", "Int:id:unique_key");
        fields.put("firstName", "String:first_name");
        fields.put("lastName", "String:last_name");
        fields.put("email", "String:email");
        fields.put("phone", "String:phone");
        fields.put("salary", "Double:salary");
        
        relationships.put("course","mentor_id:course_id:mentor_course");
    }
    
     public List<Base> courses(){
        try {
            return this.belongsToMany("course", new Course());
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
