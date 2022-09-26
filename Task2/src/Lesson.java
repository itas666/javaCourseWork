import java.time.LocalDateTime;

public class Lesson{
    //Attributes
    LocalDateTime lessonDateTime;
    //Constructor
    //Check for the hour to be between 9 and 18 in constructor
    public Lesson(LocalDateTime lessonDateTime) throws Exception{
        if((lessonDateTime.getHour() < 9) && (lessonDateTime.getHour() > 18)){
            throw new Exception("Lesson outside allowed hours");
        }
        this.lessonDateTime = lessonDateTime;
    }
    //Setters and getters
    
    //Other methods
}