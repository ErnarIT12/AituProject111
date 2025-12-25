import java.util.List;
import java.util.ArrayList;

public class Library {
    //Attributes
    private int libraryId;
    private String libraryName;
    private String libraryAddress;
    private boolean isOpen;
    private List<EBook> books;
    private List<LibraryUser> users;

    //init or construct
    public Library(int libraryId, String libraryAddress, String libraryName, boolean isOpen) {
        this.libraryId = libraryId;
        this.libraryAddress = libraryAddress;
        this.libraryName = libraryName;
        this.isOpen = isOpen;
        this.books = new ArrayList<EBook>();
        this.users = new ArrayList<LibraryUser>();
    }

    //Getter
    public int getLibraryId() {
        return libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    //Setter
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    //Add book in system
    public void addBook(EBook book) {
        this.books.add(book);
        System.out.println("Added to catalog: " + book.getTitle());
    }

    //Show catalog
    public void showCatalogs() {
        System.out.println(libraryName + " Catalog:");
        if (books.isEmpty()) {
            System.out.println("The catalog is currently empty.");
        } else {
            for (EBook book : books) {
                System.out.println("- " + book.getTitle());
            }
        }
    }

    public void compareLibraryCapacity(Library other) {
        if (this.books.size() > other.books.size()) {
            System.out.println("\n" + this.libraryName + " has more digital resources");

        } else {
            System.out.println("\n" + other.libraryName + " has more digital resources");
        }
    }

    public void registerUser(LibraryUser user) {
        this.users.add(user);
    }

    public void showAllUsers() {
        System.out.println("\n--- List of users in Library ---");
        for (LibraryUser user : users) {
            System.out.println(user.toString());
        }


    }
    public void findUser(int id){
        for(LibraryUser user : users){
            if(user.getUserId() == id){
                System.out.println("User found : " + user);
                System.out.println("His limit books: " + user.getBorrowLimit());
                return;
            }
        }
        System.out.println("User with ID " + id + " not founded.");
    }
}
