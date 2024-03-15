package bookkeeper;

import java.io.Serializable;

/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 */

public class Book implements Serializable {

    String title, authors, isbn, genre;
    double price;
    int year;

    public Book(String[] bookDetails) {
        this.title = bookDetails[0];
        this.authors = bookDetails[1];
        this.price = Double.parseDouble(bookDetails[2]);
        this.isbn = bookDetails[3];
        this.genre = bookDetails[4];
        this.year = Integer.parseInt(bookDetails[5]);
    }

}
