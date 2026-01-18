import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("======= DIGITAL LIBRARY SYSTEM (UPDATED) =======");

        DatabaseHandler db = new DatabaseHandler();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== MENU ===");
            // Раздел Пользователи
            System.out.println("1. [USERS] Show All");
            System.out.println("2. [USERS] Add Student");
            System.out.println("3. [USERS] Add Teacher");
            System.out.println("4. [USERS] Delete by ID");
            System.out.println("5. [USERS] Update Name (NEW)"); // Новый пункт
            System.out.println("-----------------------");
            // Раздел Книги
            System.out.println("6. [BOOKS] Show All");
            System.out.println("7. [BOOKS] Add Book");
            System.out.println("8. [BOOKS] Delete Book by ISBN");
            System.out.println("9. [BOOKS] Update Book (NEW)"); // Новый пункт
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            // Проверка на ввод числа
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next(); // очистка неверного ввода
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после числа

            switch (choice) {
                // --- ЛЮДИ (USERS) ---
                case 1:
                    List<LibraryUser> users = db.getAllUsers();
                    if (users.isEmpty()) System.out.println("No users found.");
                    else for (LibraryUser u : users) System.out.println(u);
                    break;

                case 2:
                    System.out.print("Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Year of study: ");
                    if (scanner.hasNextInt()) {
                        int year = scanner.nextInt();
                        db.addUser(new Student(0, sName, year));
                    } else {
                        System.out.println("Invalid year!");
                        scanner.next();
                    }
                    break;

                case 3:
                    System.out.print("Name: ");
                    String tName = scanner.nextLine();
                    System.out.print("Department: ");
                    String dept = scanner.nextLine();
                    db.addUser(new Teacher(0, tName, dept));
                    break;

                case 4:
                    System.out.print("Enter User ID to delete: ");
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        db.deleteUser(id);
                    } else {
                        System.out.println("Invalid ID!");
                        scanner.next();
                    }
                    break;

                case 5: // ОБНОВЛЕНИЕ ПОЛЬЗОВАТЕЛЯ
                    System.out.print("Enter User ID to update: ");
                    if (scanner.hasNextInt()) {
                        int updateId = scanner.nextInt();
                        scanner.nextLine(); // очистка
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        db.updateUser(updateId, newName);
                    } else {
                        System.out.println("Invalid ID!");
                        scanner.next();
                    }
                    break;

                // --- КНИГИ (BOOKS) ---
                case 6:
                    List<EBook> books = db.getAllBooks();
                    if (books.isEmpty()) System.out.println("No books in library.");
                    else for (EBook b : books)
                        System.out.println(" \"" + b.getTitle() + "\" by " + b.getAuthor() + " (ISBN: " + b.getIsbn() + ")");
                    break;

                case 7:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    db.addBook(new EBook(title, isbn, author));
                    break;

                case 8:
                    System.out.print("Enter ISBN to delete: ");
                    String delIsbn = scanner.nextLine();
                    db.deleteBook(delIsbn);
                    break;

                case 9: // НОВАЯ ФУНКЦИЯ: ОБНОВЛЕНИЕ КНИГИ
                    System.out.print("Enter ISBN of the book to update: ");
                    String updateIsbn = scanner.nextLine();
                    System.out.print("Enter New Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter New Author: ");
                    String newAuthor = scanner.nextLine();
                    db.updateBook(updateIsbn, newTitle, newAuthor);
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Saving and exiting... Good bye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}