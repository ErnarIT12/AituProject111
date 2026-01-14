import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("======= Library System (DB Version) =======");

        DatabaseHandler db = new DatabaseHandler();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1. Show All Users");
            System.out.println("2. Add Student");
            System.out.println("3. Add Teacher");
            System.out.println("4. Delete User");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            // Проверка на ввод букв
            if (!scanner.hasNextInt()) {
                System.out.println("Enter a number!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Убираем Enter

            switch (choice) {
                case 1:
                    List<LibraryUser> users = db.getAllUsers();
                    if (users.isEmpty()) System.out.println("Database is empty.");
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
                    System.out.print("Department: ");
                    String dept = scanner.nextLine();
                    db.addUser(new Teacher(0, tName, dept));
                    break;

                case 4:
                    System.out.print("ID to delete: ");
                    int id = scanner.nextInt();
                    db.deleteUser(id);
                    break;

                case 0:
                    isRunning = false;
                    break;
            }
        }
        scanner.close();
    }
}