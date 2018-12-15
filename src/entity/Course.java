package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import helpers.DateManager;

public class Course extends Base {

    private static String TB = "courses";
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    static private String DISPLAY_DATE_FORMAT = "dd.MM.yyyy";

    private int courseId;
    private String title;
    private Date startDate;
    private Date endDate;
    private int availableSpots;
    private double price;
    private String description;

//	private int mentorId;
//	private int roomId;
    public Course() {
    }

    public Course(
            int courseId, String title,
            Date startDate, Date endDate,
            int availableSpots, 
            double price,
            String description
    ) {
        this.courseId = courseId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableSpots = availableSpots;
        this.price = price;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayStartDate() {
    	System.out.println("getStartDate on " + this);
        return new SimpleDateFormat(DISPLAY_DATE_FORMAT).format(startDate);
//    	return DateManager.toReadableDateString(this.startDate);
    }

    public String getStartDate() {
    	System.out.println("getStartDate on " + this);
        return new SimpleDateFormat(DATE_FORMAT).format(startDate);
    }

    public void setStartDate(Date startDate) {
    	this.startDate = startDate;
    	System.out.println("setStartDate on " + this);
    }

    public Date getParsedStartDate() {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(getStartDate());
        } catch (ParseException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getEndDate() {
    	System.out.println("getEndDate on " + this);
    	return new SimpleDateFormat(DATE_FORMAT).format(endDate);
//    	return DateManager.toReadableDateString(this.endDate);
    }
    public String getDisplayEndDate() {
    	System.out.println("getEndDate on " + this);
    	return new SimpleDateFormat(DISPLAY_DATE_FORMAT).format(endDate);
    }

    public void setEndDate(Date endDate) {
    	this.endDate = endDate;
    	System.out.println("setEndDate on " + this);
    }

    public Date getParsedEndDate() {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(getEndDate());
        } catch (ParseException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    @Override
    public String toString(){
        return this.getTitle();
    }
    
    @Override
    protected void configure() {

        tableName = TB;

        fields.put("courseId", "Int:id:unique_key");
        fields.put("title", "String:title");
        fields.put("startDate", "Date:start_date");
        fields.put("endDate", "Date:end_date");
        fields.put("availableSpots", "Int:available_spots");
        fields.put("price", "Double:price");
        fields.put("description", "String:description");

        relationships.put("mentor","course_id:mentor_id:mentor_course");
        relationships.put("client","course_id:client_id:client_course");
    }
    
    /**
     * 
     * Many to Many relationship
     * 
     * @return 
     */
    public List<Base> clients(){
        try {
            return this.belongsToMany("client", new Client());
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * Many to Many relationship
     * 
     * @return 
     */
    public List<Base> mentors(){
        try {
            return this.belongsToMany("mentor", new Mentor());
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
