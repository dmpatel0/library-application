import java.util.HashMap;
import java.util.ArrayList;

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


}
