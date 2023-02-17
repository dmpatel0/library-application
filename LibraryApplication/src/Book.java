public class Book {

    public String title;
    public String author;
    public String genre;
    private long ISBN;
    private int pages;
    private int rating;
    private boolean completed;

    /**
     * @brief Construct a new book object (default constructor)
     */
    public Book() {
        this.title = "";
        this.author = "";
        this.genre = "";
        this.ISBN = -1;
        this.pages = -1;
        this.rating = 0;
        this.completed = false;
    }

    /**
     * @brief Construct a new book object
     *
     * @param title book title
     * @param author book author
     * @param genre book genre
     * @param ISBN book International Standard Book Number
     * @param pages book number of pages
     */
    public Book(String title, String author, String genre, long ISBN, int pages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.pages = pages;
        this.rating = 0;
        this.completed = false;
    }

    /**
     * @brief Returns an int representation of the book's genre
     * @param genre genre of book
     * @return int representation (0-4)
     */
    public int convertGenre(String genre) {
        return switch (genre.toLowerCase()) {
            case "non-fiction" -> 0;
            case "drama" -> 1;
            case "romance" -> 2;
            case "mystery" -> 3;
            case "fiction" -> 4;
            default -> -1;
        };
    }

    /**
     * @brief Marks the book as completed (read)
     */
    public void markComplete() {
        if(!this.completed) {
            this.completed = true;
        }
    }

    /**
     * @brief Rates the book
     * @param rating the rating to give the book
     * @throws IllegalArgumentException exception thrown if rating falls outside 1 and 10
     */
    public void rate(int rating) throws IllegalArgumentException {
        if(rating >=1 && rating <= 10) {
            this.rating = rating;
        } else {
            throw new IllegalArgumentException("Invalid Rating. Please enter a number between 1 - 10.");
        }
    }

    public long getISBN() {
        return this.ISBN;
    }

    public int getPages() {
        return this.pages;
    }

    public int getRating() {
        return this.rating;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String toString() {
        return "Title: " + this.title + " Author: " + this.author + " Genre: " + this.genre + " ISBN: " + this.ISBN;
    }
}
