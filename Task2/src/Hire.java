import java.time.LocalDateTime;

public class Hire{
    //Attributes
    LocalDateTime hireDateTime;

    //Constructor
    public Hire(LocalDateTime newHireDateTime) throws Exception{
        
        if((newHireDateTime.getHour() < 9) || (newHireDateTime.getHour() >= 18)){
            throw new Exception("Hire outside allowed hours");
        }
        this.hireDateTime = newHireDateTime;
    }
    //Setters and getters
    
    //Other methods
}