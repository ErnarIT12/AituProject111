import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("======= DIGITAL LIBRARY SYSTEM (FULL DB) =======");

        DatabaseHandler db = new DatabaseHandler();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. [USERS] Show All");
            System.out.println("2. [USERS] Add Student");
            System.out.println("3. [USERS] Add Teacher");
            System.out.println("4. [USERS] Delete by ID");
            System.out.println("-----------------------");
            System.out.println("5. [BOOKS] Show All");
            System.out.println("6. [BOOKS] Add Book");
            System.out.println("7. [BOOKS] Delete Book by ISBN");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // --- ЛЮДИ ---
                case 1:
                    List<LibraryUser> users = db.getAllUsers();
                    if (users.isEmpty()) System.out.println("No users found.");
                    else for (LibraryUser u : users) System.out.println(u);
                    break;
                case 2:
                    System.out.print("Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Year: ");
                    int year = scanner.nextInt();
                    db.addUser(new Student(0, sName, year));
                    break;
                case 3:
                    System.out.print("Name: ");
                    String tName = scanner.nextLine();
                    System.out.print("Dep: ");
                    String dept = scanner.nextLine();
                    db.addUser(new Teacher(0, tName, dept));
                    break;
                case 4:
                    System.out.print("ID to delete: ");
                    int id = scanner.nextInt();
                    db.deleteUser(id);
                    break;

                // --- КНИГИ ---
                case 5:
                    List<EBook> books = db.getAllBooks();
                    if (books.isEmpty()) System.out.println("No books in library.");
                    else for (EBook b : books) System.out.println(" " + b.getTitle() + " by " + b.getAuthor() + " (ISBN: " + b.getIsbn() + ")");
                    break;
                case 6:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    db.addBook(new EBook(title, isbn, author));
                    break;
                case 7:
                    System.out.print("ISBN to delete: ");
                    String delIsbn = scanner.nextLine();
                    db.deleteBook(delIsbn);
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Saving and exiting... Good bye!");
                    break;
            }
        }
        scanner.close();
    }
}