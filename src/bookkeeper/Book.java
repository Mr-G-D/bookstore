package bookkeeper;

import java.io.Serializable;

/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 */

/**
 * This class is used to store the details of a book
 */
public class Book implements Serializable {

    private String title;
    private String authors;
    private String isbn;
    private String genre;
    private double price;
    private int year;

    /**
     * Constructor to initialize the book object
     * @param bookDetails - details of the book
     */
    public Book(String[] bookDetails) {
        this.title = bookDetails[0];
        this.authors = bookDetails[1];
        this.price = Double.parseDouble(bookDetails[2]);
        this.isbn = bookDetails[3];
        this.genre = bookDetails[4];
        this.year = Integer.parseInt(bookDetails[5]);
    }

    /**
     * Overriding the toString method to return the details of the book
     * @return - details of the book
     */
    @Override
    public String toString() {
        System.out.println("Title: " + title);
        System.out.println("Authors: " + authors);
        System.out.println("Price: " + price);
        System.out.println("ISBN: " + isbn);
        System.out.println("Genre: " + genre);
        System.out.println("Year: " + year);
        return null;
    }

    /**
     * Overriding the equals method
     * @param obj - object to be checked
     * @return - true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Getter for authors
     * @return - authors of the book
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * Setter for authors
     * @param authors - authors of the book
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * Getter for ISBN
     * @return - ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Setter for ISBN
     * @param isbn - ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Getter for genre
     * @return - genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter for genre
     * @param genre - genre of the book
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Getter for price
     * @return - price of the book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price
     * @param price - price of the book
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for title
     * @return - title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title - title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Getter for year
     * @return - year of the book
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for year
     * @param year - year of the book
     */
    public void setYear(int year) {
        this.year = year;
    }
}
