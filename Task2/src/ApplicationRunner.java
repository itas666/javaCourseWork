import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationRunner {

    private static DateTimeFormatter defaultDateFormat;

    public static void main(String[] args) {
        //Initialization of our classes and loading in data
        programWrapper myData = new programWrapper();
        //Declaration of variables
        DateTimeFormatter defaultDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String option = "";
        Scanner menuReader = new Scanner(System.in);
        //Begin the menu as long as option is not 0
        do{
            System.out.println("------------------------------------");
            System.out.println("Motorboat management menu");
            System.out.println("------------------------------------");
            System.out.println("Book Lesson....................1");
            System.out.println("List member lessons............2");
            System.out.println("List instructor lessons........3");
            System.out.println("Hire motorboat for MBLH........4");
            System.out.println("Display motorboats bookings....5");
            System.out.println("See data structure.............6");
            System.out.println("Exit...........................0");
            System.out.println("------------------------------------");
            System.out.println("");
            System.out.print("Enter Choice: ");
            option = menuReader.nextLine();
            
            //Call appropriate methods for menu options
            switch(option){
                case "1":
                    bookLesson(menuReader, myData);
                    break;
                case "2":
                    showMemberLessons(menuReader, myData, defaultDateFormat);
                    break;
                case "3":
                    showInstructorLessons(menuReader, myData, defaultDateFormat);
                    break;
                case "4":
                    hireMotorboat(menuReader, myData);
                    break;
                case "5":
                    showMotorboatBookings(menuReader, myData, defaultDateFormat);
                    break;
                case "6":
                    seeAll(myData, defaultDateFormat);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid option, please try again with the options presented.");
                    System.out.println("Press enter to continue...");
                    menuReader.nextLine();
            }
        }while(!option.equals("0"));
        menuReader.close();
    }
    
    public static void hireMotorboat(Scanner menuReader, programWrapper myData){
        //Declaration of variables
        String riderName = "";
        String boatName = "";
        String hireHour = "";
        LocalDateTime hireDateTime = LocalDateTime.now();
        MBLH rider;
        Motorboat boat;
        //Normal data input for boat and rider names
        System.out.println("------------------------------------");
        System.out.println("Booking Motorboat");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.print("Enter the name of the MBLH: ");
        riderName = menuReader.nextLine();
        rider = myData.searchMBLH(riderName);
        System.out.print("Enter the name of the Motorboat: ");
        boatName = menuReader.nextLine();
        boat = myData.searchMotorboat(boatName);

        //In case null was not returned from the search functions
        if((boat != null) && (rider != null)){
            do{
                System.out.println("Enter a valid time for the booking of the Motorboat (9 to 18): ");
                hireHour = menuReader.nextLine();
            }while((Integer.parseInt(hireHour) < 9) || (Integer.parseInt(hireHour) >= 18));
            
            hireDateTime = LocalDateTime.of(LocalDateTime.now().getYear(),
                            LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(),
                            Integer.parseInt(hireHour), 00, 00);
            //Exceptions of each method controlled in the addHire method on the wrapper, except rider not found
            myData.addHire(hireDateTime, rider, boat);
            /////MORE ROBUST DATES: https://stackoverflow.com/questions/42950/how-to-get-the-last-day-of-the-month
        }else{
            System.out.println("Boat or rider not found, press enter to continue...");
            menuReader.nextLine();
        }
    }
    
    public static void bookLesson(Scanner menuReader, programWrapper myData){
        //Declaration of variables
        String alumnName = "";
        String teacherName = "";
        String lessonHour = "";
        String lessonDay = "";
        String lessonMonth = "";
        String lessonYear = "";
        String aux[];

        LocalDateTime lessonDateTime = LocalDateTime.now();
        Member alumn;
        Instructor teacher;
        //Data input for the teacher and alumn
        System.out.println("------------------------------------");
        System.out.println("Booking Lessons");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.print("Enter the name of the member: ");
        alumnName = menuReader.nextLine();
        alumn = myData.searchNovice(alumnName);
        if(alumn == null){
            alumn = myData.searchMBLH(alumnName);
        }
        
        System.out.print("Enter the name of the Instructor: ");
        teacherName = menuReader.nextLine();
        teacher = myData.searchInstructor(teacherName);

        //In case null was not returned from the search functions
        if((teacher != null) && (alumn != null)){
            do{
                System.out.println("Enter a valid date for the booking of the Motorboat (in DD-MM-YYYY format): ");
                aux = menuReader.nextLine().split("-");
                System.out.println(aux[0]);
                System.out.println(aux[1]);
                System.out.println(aux[2]);
                lessonDay = aux[0];
                lessonMonth = aux[1];
                lessonYear = aux[2];
            }while(
                (Integer.parseInt(lessonDay) < 1) || (Integer.parseInt(lessonDay) >= 31)
                &&(Integer.parseInt(lessonMonth) < 1) || (Integer.parseInt(lessonMonth) >= 12)
                &&(Integer.parseInt(lessonYear) < 2022)
                );

            do{
                System.out.println("Enter a valid time for the booking of the Motorboat (9 to 18): ");
                lessonHour = menuReader.nextLine();
            }while((Integer.parseInt(lessonHour) < 9) || (Integer.parseInt(lessonHour) >= 18));


            
            lessonDateTime = LocalDateTime.of(Integer.parseInt(lessonYear),
                            Integer.parseInt(lessonMonth), Integer.parseInt(lessonDay),
                            Integer.parseInt(lessonHour), 00, 00);
            //Exceptions of each method controlled in the addHire method on the wrapper, except rider not found
            myData.addLesson(lessonDateTime, teacher, alumn);
            /////MORE ROBUST DATES: https://stackoverflow.com/questions/42950/how-to-get-the-last-day-of-the-month
        }else{
            System.out.println("Instructor or member not found, press enter to continue...");
            menuReader.nextLine();
        }
    }
    public static void showMemberLessons(Scanner menuReader, programWrapper myData, DateTimeFormatter defaultDateFormat){
        String alumnName = "";
        Member alumn;


        System.out.println("------------------------------------");
        System.out.println("Showing Lessons");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.print("Enter the name of the member (All to see everything): ");
        alumnName = menuReader.nextLine();

        if(alumnName.equals("All")){
            myData.searchMemberLessons(null, defaultDateFormat);
            return;
        }

        alumn = myData.searchNovice(alumnName);
        if(alumn == null){
            alumn = myData.searchMBLH(alumnName);
            if (alumn == null){
                System.out.println("Member not found, printing all members");
                myData.searchMemberLessons(alumn, defaultDateFormat);
                return;
            }
        }

        myData.searchMemberLessons(alumn, defaultDateFormat);
    }
    public static void showMotorboatBookings(Scanner menuReader, programWrapper myData, DateTimeFormatter defaultDateFormat){
        String boatName = "";
        Motorboat boat;


        System.out.println("------------------------------------");
        System.out.println("Showing Motorboat Bookings");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.print("Enter the name of the member (All to see everything): ");
        boatName = menuReader.nextLine();

        if(boatName.equals("All")){
            myData.searchMotorboatBookings(null, defaultDateFormat);
            return;
        }

        boat = myData.searchMotorboat(boatName);
        if(boat == null){
            System.out.println("Motorboat not found, printing all motorboats' booked hires");
            myData.searchMotorboatBookings(boat, defaultDateFormat);
            return;
        }
        
        myData.searchMotorboatBookings(boat, defaultDateFormat);
    }
    public static void showInstructorLessons(Scanner menuReader, programWrapper myData, DateTimeFormatter defaultDateFormat){
        String instructorName = "";
        Instructor teacher;


        System.out.println("------------------------------------");
        System.out.println("Showing Instructor Lessons");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.print("Enter the name of the member (All to see everything): ");
        instructorName = menuReader.nextLine();

        if(instructorName.equals("All")){
            myData.searchInstructorLessons(null, defaultDateFormat);
            return;
        }

        teacher = myData.searchInstructor(instructorName);
        if(teacher == null){
            System.out.println("Instructor not found, printing all instructors' schedule");
            myData.searchInstructorLessons(teacher, defaultDateFormat);
            return;
        }
        myData.searchInstructorLessons(teacher, ApplicationRunner.defaultDateFormat);
    }

    public static void seeAll(programWrapper myData, DateTimeFormatter defaultDateFormat){
        ArrayList<Instructor> auxInstructors = myData.getInstructors();
        ArrayList<Motorboat> auxMotorboats = myData.getMotorboats();
        ArrayList<MBLH> auxMBLHs = myData.getMBLH();
        ArrayList<Novice> auxNovices = myData.getNovices();
        
        System.out.println("Printing instructors...");

        for(int i = 0; i < auxInstructors.size(); i++){
            System.out.println("--->" + auxInstructors.get(i).name);
            for(int j = 0; j < auxInstructors.get(i).scheduledLessons.size(); j++){
                System.out.println("...--->" +
                auxInstructors.get(i).scheduledLessons.get(j).lessonDateTime.format(defaultDateFormat)
                + "(" + auxInstructors.get(i).scheduledLessons.get(j) + ")");
            }
        }
        
        System.out.println("Printing Novices...");

        for(int i = 0; i < auxNovices.size(); i++){
            System.out.println("--->" + auxNovices.get(i).name);
            for(int j = 0; j < auxNovices.get(i).bookedLessons.size(); j++){
                System.out.println("...--->" +
                auxNovices.get(i).bookedLessons.get(j).lessonDateTime.format(defaultDateFormat)
                + "(" + auxNovices.get(i).bookedLessons.get(j) + ")");
            }
        }
        
        System.out.println("Printing motorboats...");

        for(int i = 0; i < auxMotorboats.size(); i++){
            System.out.println("--->" + auxMotorboats.get(i).name);
            for(int j = 0; j < auxMotorboats.get(i).bookedHires.size(); j++){
                System.out.println("...--->" +
                auxMotorboats.get(i).bookedHires.get(j).hireDateTime.format(defaultDateFormat)
                + "(" + auxMotorboats.get(i).bookedHires.get(j) + ")");
            }
        }
        
        System.out.println("Printing MBLHs...");

        for(int i = 0; i < auxMBLHs.size(); i++){
            System.out.println("--->" + auxMBLHs.get(i).name + "(" + auxMBLHs.get(i).bookedHires.size() + ")");
            for(int j = 0; j < auxMBLHs.get(i).bookedHires.size(); j++){
                System.out.println("...--->" +
                auxMBLHs.get(i).bookedHires.get(j).hireDateTime.format(defaultDateFormat)
                + "(" + auxMBLHs.get(i).bookedHires.get(j) + ")");
            }
        }
    }
}
