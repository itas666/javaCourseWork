import java.util.ArrayList;

public class Instructor{
    //Attributes
    String name;
    ArrayList<Lesson> scheduledLessons = new ArrayList<Lesson>();

    //Constructor
    public Instructor(String name){
        this.name = name;
    }
    //Setters and getters
    //Use the method to check if lessons exist to before we add the lesson
    //Return a boolean with the result of the operation
    public void addLessonBooking(Lesson newLesson) throws Exception{
        if(doesLessonExist(newLesson)){
            throw new Exception("The Instructor is already busy at this time, please try again");
        }
        this.scheduledLessons.add(newLesson);
    }
    //Other methods
    public boolean doesLessonExist(Lesson newLesson){
        for(int i = 0; i < this.scheduledLessons.size(); i++){
            if(newLesson.lessonDateTime.getHour() == this.scheduledLessons.get(i).lessonDateTime.getHour()){
                //Lesson exists, cannot take lessons at the same time
                return true;
            }
        }
        //Lesson doesn't exist
        return false;
    }
}