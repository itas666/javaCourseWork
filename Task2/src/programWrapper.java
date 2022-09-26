import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class programWrapper{
    //Attributes
    //One requirement is to have data that is volatile and non-volatile hard coded
    //I believe it refers to the use of private attributes along with public attributes (default)
    //We will have a private and public list, we will return both added in the getters to resolve this
    private ArrayList<Instructor> prvInstructorList = new ArrayList<Instructor>();
    private ArrayList<Motorboat> prvMotorboatList = new ArrayList<Motorboat>();
    private ArrayList<MBLH> prvMBLHList = new ArrayList<MBLH>();
    private ArrayList<Novice> prvNoviceList = new ArrayList<Novice>();
    ArrayList<Instructor> instructorList = new ArrayList<Instructor>();
    ArrayList<Motorboat> motorboatList = new ArrayList<Motorboat>();
    ArrayList<MBLH> MBLHList = new ArrayList<MBLH>();
    ArrayList<Novice> noviceList = new ArrayList<Novice>();
    
    //Constructor and initializer with default data
    public programWrapper() {
        ArrayList<Instructor> auxInstructor = new ArrayList<Instructor>();
        ArrayList<Motorboat> auxMotor = new ArrayList<Motorboat>();
        ArrayList<Novice> auxNovice = new ArrayList<Novice>();
        ArrayList<MBLH> auxMBLH = new ArrayList<MBLH>();

        //Default data to be added
        auxInstructor.add(new Instructor("Steven"));
        auxInstructor.add(new Instructor("Elena"));
        
        auxMotor.add(new Motorboat("Aboat time"));
        auxMotor.add(new Motorboat("Pier Pressure"));
        auxMotor.add(new Motorboat("Unsinkable II"));
        auxMotor.add(new Motorboat("Floating Point"));
        auxMotor.add(new Motorboat("Mer Sea"));
        
        auxNovice.add(new Novice("Joe"));
        auxNovice.add(new Novice("John Doe"));
        auxNovice.add(new Novice("Margarita"));
        
        auxMBLH.add(new MBLH("Rudolf"));
        auxMBLH.add(new MBLH("Rolf"));

        this.prvMBLHList = auxMBLH;
        this.prvMotorboatList = auxMotor;
        this.prvNoviceList = auxNovice;
        this.prvInstructorList = auxInstructor;
    }
    //Setters and getters
    //Getters with the private and public data added together first
    public ArrayList<MBLH> getMBLH(){
        ArrayList<MBLH> aux = new ArrayList<MBLH>();
        aux.addAll(prvMBLHList);
        aux.addAll(MBLHList);
        return aux;
    }
    public ArrayList<Novice> getNovices(){
        ArrayList<Novice> aux = new ArrayList<Novice>();
        aux.addAll(prvNoviceList);
        aux.addAll(noviceList);
        return aux;
    }
    public ArrayList<Motorboat> getMotorboats(){
        ArrayList<Motorboat> aux = new ArrayList<Motorboat>();
        aux.addAll(prvMotorboatList);
        aux.addAll(motorboatList);
        return aux;
    }
    public ArrayList<Instructor> getInstructors(){
        ArrayList<Instructor> aux = new ArrayList<Instructor>();
        aux.addAll(prvInstructorList);
        aux.addAll(instructorList);
        return aux;
    }


    //Other methods
    //Add values to public lists
    public void addMBLH(String name){
        MBLHList.add(new MBLH(name));
    }
    public void addNovice(String name){
        noviceList.add(new Novice(name));
    }
    public void addMotorboat(String name){
        motorboatList.add(new Motorboat(name));
    }
    public void addInstructor(String name){
        instructorList.add(new Instructor(name));
    }
    
    //Methods to return just the specific object from the list
    //If not found, then return null
    public MBLH searchMBLH(String name){
        ArrayList<MBLH> aux = this.getMBLH();
        for(int i = 0; i < aux.size(); i++){
            if(aux.get(i).name.equals(name)) return aux.get(i);
        }
        return null;
    }
    public Novice searchNovice(String name){
        ArrayList<Novice> aux = this.getNovices();
        for(int i = 0; i < aux.size(); i++){
            if(aux.get(i).name.equals(name)) return aux.get(i);
        }
        return null;
    }
    public Motorboat searchMotorboat(String name){
        ArrayList<Motorboat> aux = this.getMotorboats();
        for(int i = 0; i < aux.size(); i++){
            if(aux.get(i).name.equals(name)) return aux.get(i);
        }
        return null;
    }
    public Instructor searchInstructor(String name){
        ArrayList<Instructor> aux = this.getInstructors();
        for(int i = 0; i < aux.size(); i++){
            if(aux.get(i).name.equals(name)) return aux.get(i);
        }
        return null;
    }
    
    //Methods to add lessons or hires to each corresponding object
    public boolean addLesson(LocalDateTime lessonDateTime, Instructor teacher, Member alumn){
        try{
            Lesson aux = new Lesson(lessonDateTime);

            teacher.addLessonBooking(aux);

            alumn.addLessonBooking(aux);
        } catch (Exception e){
            System.out.println("Error; could not add lesson: " + e.toString());
            return false;
        }

        return false;
    }
    public boolean addHire(LocalDateTime hireDateTime, MBLH rider, Motorboat boat){
        try{
            Hire aux = new Hire(hireDateTime);

            rider.addHireBooking(aux);

            boat.addBooking(aux);
        } catch (Exception e){
            System.out.println("Error; could not add Hire: " + e.toString());
            //Still saves the rider booking if it fails on boat
            return false;
        }
        return false;
    }

    public void searchMemberLessons(Member myMember, DateTimeFormatter defaultDateFormat){
        ArrayList<Member> memberList = new ArrayList<Member>();

        if(myMember == null){
            memberList.addAll(this.getMBLH());
            memberList.addAll(this.getNovices());
        }else{
            memberList.add(myMember);
        }

        for (int i = 0; i < memberList.size(); i++){
            System.out.println("-->" + memberList.get(i).name);
            if(memberList.get(i).bookedLessons.size() == 0){
                System.out.println("-----> This member has no booked lessons");
            }else{
                for(int j = 0; j < memberList.get(i).bookedLessons.size(); j++){
                    System.out.println("-----> " + memberList.get(i).bookedLessons.get(j).lessonDateTime.format(defaultDateFormat));
                }
            }
        }
    }

    public void searchMotorboatBookings(Motorboat myMotorboat, DateTimeFormatter defaultDateFormat){
        ArrayList<Motorboat> boatsList = new ArrayList<Motorboat>();

        if(myMotorboat == null){
            boatsList.addAll(this.getMotorboats());
        }else{
            boatsList.add(myMotorboat);
        }

        for (int i = 0; i < boatsList.size(); i++){
            System.out.println("-->" + boatsList.get(i).name);
            if(boatsList.get(i).bookedHires.size() == 0){
                System.out.println("-----> This motorboat has no booked sessions");
            }else{
                for(int j = 0; j < boatsList.get(i).bookedHires.size(); j++){
                    System.out.println("-----> " + boatsList.get(i).bookedHires.get(j).hireDateTime.format(defaultDateFormat));
                }
            }
        }
    }

    public void searchInstructorLessons(Instructor myInstructor, DateTimeFormatter defaultDateFormat){
        ArrayList<Instructor> instructorList = new ArrayList<Instructor>();

        if(myInstructor == null){
            instructorList.addAll(this.getInstructors());
        }else{
            instructorList.add(myInstructor);
        }

        for (int i = 0; i < instructorList.size(); i++){
            System.out.println("-->" + instructorList.get(i).name);
            if(instructorList.get(i).scheduledLessons.size() == 0){
                System.out.println("-----> This Instructor has no booked Lessons");
            }else{
                for(int j = 0; j < instructorList.get(i).scheduledLessons.size(); j++){
                    System.out.println("-----> " + (instructorList.get(i).scheduledLessons.get(j).lessonDateTime.format(defaultDateFormat)));
                }
            }
        }
    }
}