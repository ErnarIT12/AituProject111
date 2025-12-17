import java.util.ArrayList;
import java.util.List;
public class LibraryUser {
    //Attributes
    private int userId;
    private String username;
    private List<EBook> borrowedBooks; //it's array of Books
    //Init or Construct
    public LibraryUser(int userId, String name){
        this.userId = userId;
        this.username = username;
        this.borrowedBooks = new ArrayList<EBook>();
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


}
