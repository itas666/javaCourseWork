import java.util.ArrayList;

public class Member{
    //Attributes
    String name;
    ArrayList<Lesson> bookedLessons = new ArrayList<Lesson>();
    int maxNumberOfLessons = 3;

    //Constructor
    public Member(String name){
        this.name = name;
    }
    //Setters and getters
    //We want to have maxNumberOfLessons lessons active,
    //return a boolean with the lesson adding result
    //We also want lessons between 9 and 18 hours but this check is done at lesson creation

    public void addLessonBooking(Lesson newLesson) throws Exception{
        if(this.bookedLessons.size() > this.maxNumberOfLessons){
            if(doesLessonExist(newLesson)){
                throw new Exception("That lesson already exists for this member");
            }
            throw new Exception("Too many lessons for this member");
        }
        this.bookedLessons.add(newLesson);
    }

    //Other methods
    public boolean doesLessonExist(Lesson newLesson){
        for(int i = 0; i < this.bookedLessons.size(); i++){
            if(newLesson.lessonDateTime.getHour() == this.bookedLessons.get(i).lessonDateTime.getHour()){
                //Lesson exists, cannot take lessons at the same time
                return true;
            }
        }
        //Lesson doesn't exist
        return false;
    }

}