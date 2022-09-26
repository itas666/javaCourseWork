public class Novice extends Member{
    //Attributes
    Instructor assignedInstructor;
    //Constructor
    public Novice(String name) {
        super(name);
    }
    //Setters and getters
    //Novice instructor not used year, as lessons need an instructor as well
    //Does the novice always have the same instructor? Design choice, answer is "no" so far
    public void setInstructor(Instructor assignedInstructor){
        this.assignedInstructor = assignedInstructor;
    }
    //Other methods
}