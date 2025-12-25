public class Teacher extends LibraryUser{
    private String department;
    public Teacher(int userId, String username,String department){
        super(userId, username);
        this.department = department;
    }
    public String getDepartment() {
        return department;
    }
    @Override
    public int getBorrowLimit(){
        return 10;
    }
    @Override
    public String toString(){
        return super.toString() + " - Dep" + department;
    }
}
