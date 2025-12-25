public class Student extends LibraryUser{
    private int yearOfStudy;
    public Student(int userId, String username, int yearOfStudy){
        super(userId, username);
        this.yearOfStudy = yearOfStudy;
    }
    public int getYearOfStudy(){
        return yearOfStudy;
    }
    @Override
    public int getBorrowLimit(){
        return 5;
    }
    @Override
    public String toString() {
        return super.toString() + " - " + yearOfStudy + " course";
    }

}
