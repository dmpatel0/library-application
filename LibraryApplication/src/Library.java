import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private final HashMap<Long, Book> collection;
    private int bookCount;
    private int[] genres;

    public Library() {
        this.collection = new HashMap<Long, Book>();
        this.bookCount = 0;
        this.genres = new int[]{0, 0, 0, 0, 0};
    }

    /**
     * @brief Adds a book to the library
     * @param aBook The book to be added
     * @throws IllegalArgumentException if the genre of the book is invalid
     */
    public void addBook(Book aBook) throws IllegalArgumentException {
        if(!collection.containsValue(aBook)) {
            int type = aBook.convertGenre(aBook.genre);
            if(type < 0 || type > 4) {
                throw new IllegalArgumentException("The genre " + aBook.genre + " is not supported!");
            } else {
                this.genres[type] += 1;
            }

            this.collection.put(aBook.getISBN(), aBook);
            this.bookCount++;
        } else {
            System.out.println("A book with that ISBN already exists in the library!");
        }
    }

    public void addBook(String title, String author, String genre, long ISBN, int pages) throws IllegalArgumentException {

        Book aBook = new Book(title, author, genre, ISBN, pages);

        if(!collection.containsValue(aBook)) {
            int type = aBook.convertGenre(aBook.genre);
            if(type < 0 || type > 4) {
                throw new IllegalArgumentException("The genre " + aBook.genre + " is not supported!");
            } else {
                this.genres[type] += 1;
            }

            this.collection.put(aBook.getISBN(), aBook);
            this.bookCount++;
        } else {
            System.out.println("A book with that ISBN already exists in the library!");
        }
    }

    /**
     * @brief Removes a book from the library
     * @param ISBN of the book to remove
     * @throws IllegalArgumentException if the book doesn't exist in the library
     */
    public void removeBook(long ISBN) throws IllegalArgumentException {
        if(collection.containsKey(ISBN)) {
            Book remBook = collection.get(ISBN);
            int genre = remBook.convertGenre(remBook.genre);
            this.genres[genre] -= 1;
            this.bookCount--;
            this.collection.remove(ISBN);
        } else {
            throw new IllegalArgumentException("The ISBN " + ISBN + " doesn't exist in the library!" );
        }
    }

    /**
     * @brief Returns a list of books by a given author
     * @param author
     * @return
     */
    public ArrayList<Book> findByAuthor(String author) {

        ArrayList<Book> booksByAuthor = new ArrayList<Book>();

        this.collection.forEach((k, v) -> {
            if(v.author.equals(author)) {
                booksByAuthor.add(v);
            }
        });

        return booksByAuthor;
    }

    /**
     * @brief Returns a list of books of a given genre
     * @param genre
     * @return
     */
    public ArrayList<Book> findByGenre(String genre) {

        ArrayList<Book> booksByGenre = new ArrayList<Book>();

        this.collection.forEach((k, v) -> {
            if(v.genre.equals(genre)) {
                booksByGenre.add(v);
            }
        });

        return booksByGenre;
    }

    /**
     * @brief Will display the current genres kept in the library, and the quantity of each
     *
     */
    public void displayContents() {

        if(this.bookCount == 1) {
            System.out.println("There is currently 1 book in the library:");
        } else {
            System.out.println("There are currently " + this.bookCount + " books in the library:");
        }

        for(int i = 0; i < 5; i++) {
            int genreAmount = this.genres[i];
            if(genreAmount > 0) {
                String genre = switch (i) {
                    case 0 -> "Non-fiction";
                    case 1 -> "Drama";
                    case 2 -> "Romance";
                    case 3 -> "Mystery";
                    case 4 -> "Fiction";
                    default -> "Unknown";
                };

                if(genreAmount == 1) {
                    System.out.println("There is " + genreAmount + " book of the " + genre + " genre.");
                } else {
                    System.out.println("There are " + genreAmount + " books of the " + genre + " genre.");
                }
            }
        }
    }

    /**
     * @brief Reads the given file and adds books to the library with the correct information
     *
     * @param filePath The location of the file to be read
     */
    public void readFile(String filePath) throws FileNotFoundException {
        File readFile = new File(filePath);
        Scanner s = new Scanner(readFile);

        while(s.hasNextLine()) {
            String nextBook = s.nextLine();
            String[] stringArray = nextBook.split(":");

            String title = stringArray[0];
            String author = stringArray[1];
            String genre = stringArray[2];
            long ISBN = Long.parseLong(stringArray[3]);
            int pages = Integer.parseInt(stringArray[4]);

            this.addBook(title, author, genre, ISBN, pages);
        }
    }

    /**
     * @brief Writes the current library into the file that stores the data
     */
    public void writeFile(String filePath) throws IOException {
        try {

            FileWriter w = new FileWriter(filePath, false);
            this.collection.forEach((k, v) -> {
                String writeLine = v.title + ":" + v.author + ":" + v.genre + ":" + String.valueOf(v.getISBN()) + ":" + String.valueOf(v.getPages()) + "\n";
                try {
                    w.write(writeLine);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            w.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
