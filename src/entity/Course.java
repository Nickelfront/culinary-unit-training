package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Course extends Base {

    static private String TB = "courses";
    static private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
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

    public String getStartDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(endDate);
    }

    public Date getParsedStartDate() {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(getStartDate());
        } catch (ParseException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Date getParsedEndDate() {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(getEndDate());
        } catch (ParseException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
      
    }
}
