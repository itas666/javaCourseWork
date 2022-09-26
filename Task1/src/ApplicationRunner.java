
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class ApplicationRunner {
    public static ArrayList<BookerPrize> init(String filePath){
        try{
            File myFile = new File(filePath);
            Scanner fileReader = new Scanner(myFile);

            ArrayList<BookerPrize> results = new ArrayList<BookerPrize>();

            //Use auxiliary string to save data temporarily
            //Use an auxiliary array of string for the split
            //Save the regex splitter compatible with all line records
            String regexSplit = "(:|, |,|\\(|\\)|\\|)";
            String regexSplitPerson = "(,)";
            String aux = "";
            String[] auxArray;
            ArrayList<Book> bookerPrizeShortList = new ArrayList<Book> (); 
            String bookerPrizeYear = "";
            String bookerPrizeAuthor = "";
            String bookerPrizeTitle = "";
            String bookerPrizePublisher = "";
            String normalBookName = "";
            String normalBookAuthor = "";
            String normalBookPublisher = "";
            String bookerPrizeChairPerson = "";
            ArrayList<String> bookerPrizePanel = new ArrayList<String>();
            int line = 0;
            //Structure
            //Line 1: Year:Author, Book(Publisher)
            //Line 2: Author, Book|Author, Book [...]
            //Line 3: Person, Person, Person(chair)

            while(fileReader.hasNext()){
                    aux = fileReader.nextLine();

                    switch(line%3){
                        case 0:
                            auxArray = aux.split(regexSplit);

                            bookerPrizeYear = auxArray[0];
                            bookerPrizeAuthor = auxArray[1];
                            bookerPrizeTitle = auxArray[2];
                            bookerPrizePublisher = auxArray[3];
                        break;
                        case 1:
                            bookerPrizePanel = new ArrayList<String>();
                            bookerPrizeShortList = new ArrayList<Book>();

                            auxArray = aux.split(regexSplit);
                            
                            for(int i = 0; i < auxArray.length; i++){
                                normalBookAuthor = auxArray[i];
                                i++;
                                normalBookName = auxArray[i];
                                i++;
                                normalBookPublisher = auxArray[i];
                                i++;
                                //We remove the extra ) in the split, that creates extra record in array

                                bookerPrizeShortList.add(new Book(normalBookName, normalBookAuthor, normalBookPublisher));
                            }
                        break;
                        case 2:
                            auxArray = aux.split(regexSplitPerson);
                            
                            for(int i = 0; i < auxArray.length; i++){
                                if(auxArray[i].contains("(chair)")){
                                    bookerPrizeChairPerson = auxArray[i].replace("(chair)", "");
                                    //pop arraylist to delete it from the normal list
                                    //add to main booker price record as chair person
                                }else{
                                    bookerPrizePanel.add(auxArray[i]);
                                }
                            }
                            //The loop has all needed data to create our bookerPrize record
                            results.add(
                                new BookerPrize(
                                    Integer.parseInt(bookerPrizeYear),
                                    new Book(bookerPrizeTitle, bookerPrizeAuthor, bookerPrizePublisher),
                                    bookerPrizeShortList,
                                    bookerPrizePanel,
                                    bookerPrizeChairPerson));

                                    
                        break;
                    }
                    line++;
            }

            fileReader.close();

            return results;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void listFunction(ArrayList<BookerPrize> myData){
        //Will use formatter instead of println
        String format = "|%1$-6s|%2$-35s|%3$-20s|%4$-25s|\n";
        //Print X spaces, then replace the spaces with "-"
        String lineSeparator = String.format("%1$-91s", "").replace(" ", "-");

        System.out.println(lineSeparator);
        System.out.format(format, "Year", "Title", "Author", "Publisher");
        System.out.println(lineSeparator);
        for(int i=0; i < myData.size(); i++){
            System.out.format(format, myData.get(i).getYear(), myData.get(i).getWinnerTitle(), myData.get(i).getWinnerAuthor(), myData.get(i).getWinnerPublisher());
        }
        System.out.println(lineSeparator);
    }

    public static void selectYearFunction(ArrayList<BookerPrize> myData){
        //Aux variables
        String option = "";
        Scanner menuReader = new Scanner(System.in);

        System.out.println("");
        System.out.print("Enter year of prize winner: ");
        option = menuReader.nextLine();

        
        for(int i = 0; i < myData.size(); i++){
            if(Integer.parseInt(option) == myData.get(i).getYear()){
                System.out.println(myData.get(i).toString());
                return;
            }
        }
        System.out.println("Year not found, returning to menu");
    }

    
    public static void searchBook(ArrayList<BookerPrize> myData){
        //Aux variables
        String option = "";
        Scanner menuReader = new Scanner(System.in);
        String format = "|%1$-40s|%2$-25s|%3$-20s|%4$-10s|";
        String lineSeparator = String.format("%1$-100s", "").replace(" ", "-");
        StringBuilder results = new StringBuilder();

        results.append(lineSeparator);
        results.append("\n");
        results.append(
            String.format(format, "Title", "Author", "Status", "Year")
        );
        results.append("\n");
        results.append(lineSeparator);
        results.append("\n");

        System.out.println("");
        System.out.print("Enter the title or partial book title: ");
        option = menuReader.nextLine();

        
        for(int i = 0; i < myData.size(); i++){
            results.append(myData.get(i).searchBook(option));
        }
        System.out.println(results.toString());
    }


    public static void main(String[] args) {
        String dataFile = System.getProperty("user.dir") + File.separator + "booker-data.txt";
        
        //Initialization of our class and loading in data
        ArrayList<BookerPrize> myData = new ArrayList<BookerPrize>();
        myData = init(dataFile);

        //Declaration of variables
        String option = "";
        Scanner menuReader = new Scanner(System.in);
        //Begin the menu as long as option is not 0
        do{
            System.out.println("-------------------");
            System.out.println("Booker Prize Menu");
            System.out.println("-------------------");
            System.out.println("List...............1");
            System.out.println("Select.............2");
            System.out.println("Search.............3");
            System.out.println("Exit...............0");
            System.out.println("-------------------");
            System.out.println("");
            System.out.print("Enter Choice: ");
            option = menuReader.nextLine();
            
            //Call appropriate methods for menu options
            switch(option){
                case "1":
                    listFunction(myData);
                    break;
                case "2":
                    selectYearFunction(myData);
                    break;
                case "3":
                    searchBook(myData);
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
}
