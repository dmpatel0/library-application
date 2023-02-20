import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    static Library library = new Library();
    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Library Application");
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,500);

        Scanner s = new Scanner(System.in);

        System.out.println("*** Start of program. ***");
        System.out.println();
        System.out.println("Welcome to your personal library!");
        System.out.println();


        String filePath = "./src/libFile.txt";

        try {
            library.readFile(filePath);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String action;

        do {
            System.out.println("What would you like to do?");
            System.out.println("Your options are (display) display the contents of the library, (add) add a book,\n(remove) remove a book, or (quit) quit the program. Please enter the prompt in the parenthesis.");

            action = s.nextLine();

            if(action.equals("display")) {
                System.out.println();
                library.displayContents();
                System.out.println();
            }

            if(action.equals("add")) {
                System.out.println();
                System.out.println("Please enter the title, author, genre, 13 or 10 digit ISBN (13 is preferred), and the number of pages. Press enter after each entry.");
                System.out.println("Supported genres include Non-fiction, Drama, Romance, Mystery, and Fiction.");

                String title = s.nextLine();
                String author = s.nextLine();
                String genre = s.nextLine();
                long ISBN = Long.parseLong(s.nextLine());
                int pages = Integer.parseInt(s.nextLine());

                try {
                    library.addBook(title, author, genre, ISBN, pages);
                    System.out.println("\nBook successfully added!");
                    System.out.println();
                } catch (IllegalArgumentException e) {
                    System.out.println("\n" + e);
                }
            }

            if(action.equals("remove")) {
                System.out.println("\nWhat is the ISBN of the book you would like to remove? Please enter the 13 digit ISBN if possible, otherwise 10 will suffice.");
                long ISBN = Long.parseLong(s.nextLine());

                try {
                    library.removeBook(ISBN);
                    System.out.println("\nBook successfully removed!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter an ISBN present in the library, or double check the inputted ISBN for errors.\n");
                }
            }

        } while (!(action.equals("quit")));

        try {
            library.writeFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("*** End of program ***");
    }

    public static void displayContents() {
        System.out.println();
        library.displayContents();
        System.out.println();
    }
}
