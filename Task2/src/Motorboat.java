import java.util.ArrayList;

public class Motorboat{
    //Attributes
    String name;
    ArrayList<Hire> bookedHires = new ArrayList<Hire>();
    
    //Constructor
    public Motorboat(String name){
        this.name = name;
    }
    //Setters and getters
    public void addBooking(Hire newHire) throws Exception{
        if(this.doesHireExist(newHire)){
            throw new Exception("There's already an identical booking for the boat, please try again");
        }
        this.bookedHires.add(newHire);
    }
    
    //Other methods
    public boolean doesHireExist(Hire newHire){
        for(int i = 0; i < this.bookedHires.size(); i++){
            if(newHire.hireDateTime.getHour() == this.bookedHires.get(i).hireDateTime.getHour()){
                //Hire exists, cannot take hire at the same time
                return true;
            }
        }
        //Hire doesn't exist
        return false;
    }
}