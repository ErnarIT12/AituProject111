import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class LibraryUser {
    //Attributes
    private int userId;
    private String username;
    //arrays
    private List<LibraryUser> users;
    private List<EBook> borrowedBooks;
    //Init or Construct
    public LibraryUser(int userId, String username){
        this.userId = userId;
        this.username = username;
        this.borrowedBooks = new ArrayList<EBook>();
    }
    //Default for class
    public int getBorrowLimit(){
        return 3;
    }
    //Override Object method
    @Override
    public String toString() {
        return "User ID: " + this.getUserId() + ", Name: " + this.getName() + " (" + this.getClass().getSimpleName() + ")";
    }
    //equals by class and id
    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || this.getClass() != object.getClass()) return false;
        LibraryUser other = (LibraryUser) object;
        return this.getUserId() == other.getUserId();

    }
    @Override
    public int hashCode(){
        return Integer.hashCode(getUserId());
    }
    //Register user to List
    public void registerUser(LibraryUser user){
        this.users.add(user);
    }
    //Find user to List
    public LibraryUser findUserById(int id){
        for(LibraryUser user : users ){
            if(user.getUserId() == id)return user;
        }
        System.out.println("User is not founded!");
        return null;
    }

    //Getter
    public int getUserId(){
        return userId;
    }
    public String getName(){
        return username;
    }
    //Setter
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void  setUsername(String username){
        this.username = username;
    }
    public void showAllUsers(){
        System.out.println("\n--- List of users in Library ---");
        for(LibraryUser user : users){
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
