package entity;

import java.util.Date;

public class Clients extends Base {
    static public String TB = "clients";
    
    private int clientId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String phone;

    public Clients() {
        super();
    }

    public Clients(int clientId, String firstName, String lastName, Date birthDate, String email, String phone) {
        super();

        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
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

    public String getBirthDate() {
        return "2016-01-01";
//		return birthDate;
    }

    public void setBirthDate(Date age) {
        this.birthDate = age;
    }

    @Override
    protected void configure() {
        tableName = TB;

        fields.put("clientId", "id");
        fields.put("firstName", "first_name");
        fields.put("lastName", "last_name");
        fields.put("email", "email");
        fields.put("birthDate", "birth_date");
        fields.put("phone", "phone");
    }

}
