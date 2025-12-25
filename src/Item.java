public class Item {
    private String title;
    private String  isbn;
    private String author;
    private boolean available;
    //Construct
    public Item(String title, String isbn, String author ){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }
    //Getter
    public String getTitle(){return title;}
    public String getIsbn(){return isbn;}
    public String getAuthor(){return author;}
    public boolean getAvailable(){return available;}
    //Setter
    public void setTitle(String title){this.title = title;}
    public void setIsbn(String isbn){this.isbn = isbn;}
    public void setAuthor(String author){this.author = author;}
    public void setAvailable(boolean available){this.available = available;}
}
