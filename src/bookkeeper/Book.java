package bookkeeper;

import java.io.Serializable;

/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 */

public class Book implements Serializable {

    private String title;

    private String authors;
    private String isbn;
    private String genre;

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private double price;
    private int year;

    public Book(String[] bookDetails) {
        this.title = bookDetails[0];
        this.authors = bookDetails[1];
        this.price = Double.parseDouble(bookDetails[2]);
        this.isbn = bookDetails[3];
        this.genre = bookDetails[4];
        this.year = Integer.parseInt(bookDetails[5]);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
