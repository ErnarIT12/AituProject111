public class Main {
    public static void main(String[] args) {
        System.out.println("=======Digital Library System ERNAR=======\n");
        //Create one Library
        Library aituLib = new Library(1, "Mangilik El, 55", "AituLibary", true);
        Library nuLib = new Library(2, "Kabanbai Batyra 53", "NuLibrary", true);
        //Create some books
        EBook b1 = new EBook("Fahrenheit 451", "312321323132", " Ray Douglas Bradbury");
        EBook b2 = new EBook("Crime and Punishment", "43543543534", "Fyodor Dostoevsky");
        //Add books to Library
        aituLib.addBook(b1);
        aituLib.addBook(b2);
        nuLib.addBook(b1);
        aituLib.showCatalogs();
        //Compare Library Aitu vs NU
        aituLib.compareLibraryCapacity(nuLib);
    }
}