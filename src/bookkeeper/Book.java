package bookkeeper;

/**
 * @author Sanjay Bharathi
 */

public class Book {

    String title, authors, price, isbn, genre, year;

    public Book(String[] bookDetails) {
        this.title = bookDetails[0];
        this.authors = bookDetails[1];
        this.price = bookDetails[2];
        this.isbn = bookDetails[3];
        this.genre = bookDetails[4];
        this.year = bookDetails[5];
    }

}
