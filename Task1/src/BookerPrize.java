import java.util.List;
import java.util.ArrayList;

public class BookerPrize {
//Attributes
    int year;
    Book winner;
    ArrayList<Book> shortList = new ArrayList<Book>();
    ArrayList<String> panel = new ArrayList<String>();
    String chairPerson;

//Constructor
    public BookerPrize(int y, Book w, ArrayList<Book> sl, ArrayList<String> p, String cp){
        this.year = y;
        this.winner = w;
        this.shortList = sl;
        this.panel = p;
        this. chairPerson = cp;
    }
    
//Methods
    public int getYear(){ return this.year; }
    public Book getWinner(){ return this.winner; }
    public String getWinnerTitle(){ return this.winner.getTitle(); }
    public String getWinnerAuthor(){ return this.winner.getAuthor(); }
    public String getWinnerPublisher(){ return this.winner.getPublisher(); }
    public List<Book> getShortList(){ return this.shortList; }
    public List<String> getPanel(){ return this.panel; }
    public String getChairPerson(){ return this.chairPerson; }
    
    public String searchBook(String option){
        //The program will use formatter to append to a stringbuilder
        //This will set different formats and format strings for separators
        String format = "|%1$-40s|%2$-25s|%3$-20s|%4$-10s|";
        String lineSeparator = String.format("%1$-100s", "").replace(" ", "-");
        StringBuilder result = new StringBuilder();
        
        if(this.getWinnerTitle().toUpperCase().contains(option.toUpperCase())){
            result.append(
                String.format(format,
                            this.getWinnerTitle().replaceAll("(?i)"+option, option.toUpperCase()),
                            this.getWinnerAuthor(), "Winner", this.year)
            );
            result.append("\n");
            result.append(lineSeparator);
            result.append("\n");
        }

        for( int i = 0;i < this.shortList.size(); i++){
            if(this.shortList.get(i).getTitle().toUpperCase().contains(option.toUpperCase())){
                result.append(
                    String.format(format,
                                this.shortList.get(i).getTitle().replaceAll("(?i)"+option, option.toUpperCase()),
                                this.shortList.get(i).getAuthor(), "Shortlisted", this.year)
                );
                result.append("\n");
                result.append(lineSeparator);
                result.append("\n");
            }
        }
        return result.toString();
    }

    public String toString(){
            String panelPerson = "";
            String chairPerson = "";
            int shortListSize = (int) this.shortList.size();
            int panelSize = (int) this.panel.size();
            StringBuilder selectedYearText = new StringBuilder();
            //The program will use formatter to append to a stringbuilder
            //This will set different formats and format strings for separators
            String format = "|%1$-20s|%2$-45s|%3$-25s|%4$-25s|%5$-25s|";
            //Print X spaces, then replace the spaces with "-"
            String lineSeparator = String.format("%1$-146s", "").replace(" ", "-");

            selectedYearText.append(lineSeparator);
            selectedYearText.append("\n");
            
            //Header and winner
            selectedYearText.append(lineSeparator);
            selectedYearText.append("\n");
            selectedYearText.append(String.format(format, "Author",
                                    "Book Title", "Publisher", "Chair", "Panel"));
            selectedYearText.append("\n");
            selectedYearText.append(lineSeparator);
            selectedYearText.append("\n");
            selectedYearText.append(String.format(format, this.getWinnerAuthor().toUpperCase(),
            this.getWinnerTitle().toUpperCase(), this.getWinnerPublisher().toUpperCase(), "", ""));
            selectedYearText.append("\n");
            //Specific separator for the winner book
            selectedYearText.append(String.format("%1$-93s|", "").replace(" ", "-") +
            String.format("%1$-25s|", "") +
            String.format("%1$-25s|", ""));
            selectedYearText.append("\n");
            //header and winner

            for(int i=0, j = 0; i < shortListSize; i++){
                //If the selected year panel has not been printed (counter j is less than the size)
                //Print a panel person until the end
                if(j < panelSize){
                    panelPerson = this.panel.get(j);
                    j++;
                }

                //If the iteration is the middle one, print the chairperson (centered)
                if (i == (int) Math.round((int) (shortListSize-2)/2)){
                    chairPerson = this.getChairPerson();
                }

                selectedYearText.append(String.format(format,
                this.shortList.get(i).getAuthor(),this.shortList.get(i).getTitle(),
                this.shortList.get(i).getPublisher(),chairPerson, panelPerson));
                selectedYearText.append("\n");

                //Reset auxiliary variables for person printing
                panelPerson = "";
                chairPerson = "";
            }
            selectedYearText.append(lineSeparator);
            selectedYearText.append("\n");
            
            return selectedYearText.toString();
    }
}
