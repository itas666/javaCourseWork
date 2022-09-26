import java.util.ArrayList;

public class MBLH extends Member{
    //Attributes
    ArrayList<Hire> bookedHires = new ArrayList<Hire>();
    int maxNumberOfHires = 2;

    //Constructor
    public MBLH(String name) {
        super(name);
    }
    //Setters and getters
    public void addHireBooking(Hire newHire) throws Exception{
        //If we don't have enough bookings and can add a new one
        if(this.bookedHires.size() < this.maxNumberOfHires){
            //And if there's not an identical booking
            if(this.doesHireExist(newHire)){
                throw new Exception("An identical booking already exists, try again.");
            }
            //Assign booking to our array list of hires
            this.bookedHires.add(newHire);
            return;
        }
        throw new Exception("Too many bookings for this member.");
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