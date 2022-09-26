public class Book {
    String title;
    String author;
    String publisher;

    public Book(String t, String a, String p){
        this.title = t;
        this.author = a;
        this.publisher = p;
    }
    
    public String getTitle(){ return this.title; }
    public String getAuthor(){ return this.author; }
    public String getPublisher(){ return this.publisher; }
}
