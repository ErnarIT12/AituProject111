import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("=======Digital Library System ERNAR=======");
        Scanner scanner = new Scanner(System.in);
        Library aituLib = new Library(1, "Mangilik El, 55", "AituLibary", true);
        System.out.println("======= Welcome to the " + aituLib.getLibraryName() + " =======");
        boolean isRunning = true;
        while (isRunning){
            System.out.println("\n Choose options :");
            System.out.println("1. Add book");
            System.out.println("2. Show all books");
            System.out.println("3. Register user");
            System.out.println("4. Show all users");
            System.out.println("5. Find user by ID");
            System.out.println("0. Escape");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.print("Name: ");
                    String title = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    aituLib.addBook(new EBook(title, isbn, author));
                    break;

                case 2:
                    aituLib.showCatalogs();
                    break;
                case 3:
                    System.out.println("Who are we registering?");
                    System.out.println("1 - Student");
                    System.out.println("2 - Teacher");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Enter the course of study (in numbers): ");
                        int year = scanner.nextInt();
                        Student s = new Student(id, name, year);
                        aituLib.registerUser(s);
                    } else if (type == 2) {
                        System.out.print("Enter the department (text): ");
                        String dept = scanner.nextLine();
                        Teacher t = new Teacher(id, name, dept);
                        aituLib.registerUser(t);
                    } else {
                        System.out.println("Error selecting type.");
                    }
                    break;
                case 4:
                    aituLib.showAllUsers();
                    break;
                case 5:
                    System.out.print("Enter ID to search: ");
                    int searchId = scanner.nextInt();
                    aituLib.findUser(searchId);
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
        scanner.close();
    }

}