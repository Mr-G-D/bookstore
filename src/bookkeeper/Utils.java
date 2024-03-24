package bookkeeper;

import bookkeeper.exceptions.SemanticErrorException;
import bookkeeper.exceptions.SyntaxErrorException;
import bookkeeper.exceptions.semantic.BadIsbn10Exception;
import bookkeeper.exceptions.semantic.BadIsbn13Exception;
import bookkeeper.exceptions.semantic.BadPriceException;
import bookkeeper.exceptions.semantic.BadYearException;
import bookkeeper.exceptions.syntax.MissingFieldException;
import bookkeeper.exceptions.syntax.TooFewFieldsException;
import bookkeeper.exceptions.syntax.TooManyFieldsException;
import bookkeeper.exceptions.syntax.UnknownGenreException;
import exceptions.ExitViewingCommandException;

import java.io.*;
import java.util.Scanner;

import static bookkeeper.Constants.*;

/**
 * @author DINESH KUMAR
 */

public class Utils {
    /**
     * This method checks for all Semantic errors
     * @param bookName Book details
     * @throws SemanticErrorException when a Semantic error is identified
     */
    public static void checkForSemantics(String[] bookName) throws SemanticErrorException {
        if(!checkPrice(Double.parseDouble(bookName[2]))){
            throw new BadPriceException();
        }
        if (!checkYear(Integer.parseInt(bookName[5]))){
            throw new BadYearException();
        }
        if(bookName[3].length() == 10){
            if (!checkISBN10(bookName[3])){
                throw new BadIsbn10Exception();
            }
        } else {
            if (!checkISBN13(bookName[3])){
                throw new BadIsbn13Exception();
            }
        }
    }

    /**
     * Method to check price of book
     * @param price price of book
     * @return true if valid, false if not
     */
    public static boolean checkPrice(double price){
        return price > 0;
    }

    /**
     * Method to check the year
     * @param year Year of book published
     * @return true if valid, false if not
     */
    public static boolean checkYear(int year){
        return (year > 1995 && year < 2024);
    }

    /**
     * Method to check the ISBN of book in ISBN 10 format
     * @param isbn ISBN of book
     * @return true if valid, false if not
     */
    public static boolean checkISBN10(String isbn){
        try {
            long num = Long.parseLong(isbn);
            int x = 1;
            int sum = 0;
            while (num > 0){
                long digit = num % 10;
                num = num / 10;
                sum += (int) (digit * x++);
            }
            return sum % 11 == 0;
        }catch (NumberFormatException e){
            return false;
        }

    }

    /**
     * Method to check the ISBN of book in ISBN 13 format
     * @param isbn ISBN of book
     * @return true if valid, false if not
     */
    public static boolean checkISBN13(String isbn){
        try {
            long num = Long.parseLong(isbn);
            int sum = 0;
            for (int i = 0; i < 13; i++) {
                long digit = num % 10;
                num = num / 10;
                if (i % 2 == 0){
                    sum += (int) digit;
                } else {
                    sum += (int) (digit * 3);
                }
            }
            return sum % 10 == 0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * This method handles the caught semantic error exception
     * @param filename File name in which error is identified
     * @param book book record from CSV
     * @param e SemanticErrorException caught
     */
    public static void handleSemanticError(String filename, String book, SemanticErrorException e) {
        PrintWriter writer = FileWriterFactory.getSemanticErrorFileWriter(filename);
        writer.print("Error: ");
        writer.println(e.getMessage());
        writer.print("Record: ");
        writer.println(book);
    }

    /**
     * Method displays the main menu
     * @param fileName File name selected
     * @return Input choice of user
     */
    public static String displayMainMenu(String fileName){
        System.out.println("-----------------------------------------------------");
        System.out.println("                     Main Menu                       ");
        System.out.println("-----------------------------------------------------");
        System.out.println("v View the selected file: " + fileName);
        System.out.println("s Select a file to view");
        System.out.println("x Exit");
        System.out.println("-----------------------------------------------------");
        System.out.println();
        System.out.print("Enter Your Choice: ");
        Scanner input = new Scanner(System.in);
        return (input.nextLine());
    }

    /**
     * Method displays the sub menu
     * @throws IOException if File cannot be read
     * @throws ClassNotFoundException when processing binary files
     */
    public static void displaySubMenu() throws IOException, ClassNotFoundException {
        System.out.println("-----------------------------------------------------");
        System.out.println("                   File Sub Menu                     ");
        System.out.println("-----------------------------------------------------");

        String[] files = Constants.binaryFileNames;

        for (int i = 0; i < files.length; i++) {
            String f = String.format("%-5d %-40s %5s", i+1, files[i], "(" + findRecords(files[i]) + " records)");
            System.out.println(f);

        }
        System.out.println("-----------------------------------------------------");
        System.out.println();
        System.out.print("Enter Your Choice: ");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        navigate(files[i-1]);
    }


    /**
     * This method navigates the menu based on user option
     * @param fileName File name selected
     * @throws IOException when reading files
     * @throws ClassNotFoundException when parsing binary files
     */
    public static void navigate(String fileName) throws IOException, ClassNotFoundException {
        String str = displayMainMenu(fileName);
        if(str.equals("v")){
            displayFile(fileName);
        } else if (str.equals("s")) {
            displaySubMenu();
        } else if (!(str.equalsIgnoreCase("x"))){
            System.out.println("Not a valid option");
            navigate(fileName);
        }
    }

    /**
     * This method iterates and displays the content of the file
     * @param fileName File name selected
     * @throws IOException when reading files
     * @throws ClassNotFoundException when parsing binary files
     */
    private static void displayFile(String fileName) throws IOException, ClassNotFoundException {
        int cursor = 0;
        Scanner kbd = new Scanner(System.in);
        String f = String.format("%-5s %-40s %5s", "Viewing", fileName, "(" + findRecords(fileName) + " records)");
        System.out.println(f);
        Book[] books = Constants.deserializedBooks[indexOf(Constants.binaryFileNames, fileName)];
        while (true) {
            System.out.print("Enter viewing command: ");
            int ip = kbd.nextInt();
            try {
                cursor = displayRange(ip, cursor, books);
            } catch (ExitViewingCommandException e) {
                break;
            }
        }
        navigate(fileName);

    }

    /**
     * This method displays range of books as per user input
     * @param ip input number of books to be viewed
     * @param cursor current cursor position
     * @param books Array of books to be viewed
     * @return cursor position after manipulation
     * @throws ExitViewingCommandException when exiting
     */
    private static int displayRange(int ip, int cursor, Book[] books) throws ExitViewingCommandException {
        if(ip==0)
            throw new ExitViewingCommandException();
        if(ip < 0){
            int x = ip * -1;
            do{
                System.out.println(books[cursor]);
                x--;
                cursor--;
            }while (cursor >= 0 && cursor < books.length && x>0);
            if(cursor<0){
                System.out.println("BOF has been reached");
            }
            cursor++;
        } else{
            do{
                System.out.println(books[cursor]);
                cursor++;
                ip--;
            }while (cursor < books.length && ip>0);
            if(cursor >= books.length){
                System.out.println("EOF has been reached");
            }
            cursor--;
        }

        return cursor;
    }

    /**
     * This method reads books from files and returns the array
     * @param fileName File name to be read
     * @return array of Books from the file
     * @throws IOException when reading files
     * @throws ClassNotFoundException when parsing binary files
     */
    private static Book[] readBooksFromFile(String fileName) throws IOException, ClassNotFoundException {
        Book[] books = new Book[findRecords(fileName)];
        int bookItr = 0;
        ObjectInputStream fileStream = new ObjectInputStream(new FileInputStream(Constants.part2OutputDirectory + File.separator + fileName));
        Book book = (Book) fileStream.readObject();
        while(book != null){
            books[bookItr++] = book;
            try {
                book = (Book) fileStream.readObject();
            }catch (EOFException e){
                book = null;
            }
        }
        return books;
    }

    /**
     * Method is used to find the record count in each file
     * @param fileName Filename
     * @return number of records in the file
     */
    public static int findRecords(String fileName){
        return Constants.recordCount[indexOf(Constants.binaryFileNames, fileName)];
    }


    /**
     * Custom indexOf implementation
     * @param array Array to be searched
     * @param searchString string to be searched
     * @return index of the string if found, -1 if not
     */
    public static int indexOf(String[] array, String searchString) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(searchString)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Reads all binary files and fills the static member array
     */
    public static void fillBooks() {
        int fileCounter = 0;
        for (String fileName: Constants.binaryFileNames) {
            try {
                Constants.deserializedBooks[fileCounter++] = readBooksFromFile(fileName);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method reads individual files and returns book array
     * @param filename CSV file to be read
     * @return array of books
     * @throws FileNotFoundException when reading CSV files
     */
    static Book[] readIndividualFile(String filename) throws FileNotFoundException {
        String[][] validBooks = new String[1000][];
        int i = 0;
        Scanner scanner = new Scanner(new File(dataDirectory + File.separator + filename));
        while (scanner.hasNext()){
            String book = scanner.nextLine();
            String[] bookDetails = book.split(csvSplitRegex);
            try {
                validBooks[i++] = checkForSyntaxErrors(bookDetails);
                BufferedWriter w = FileWriterFactory.getGenreBasedFileWriter(bookDetails[4]);
                w.write(book);
                w.write('\n');
            } catch (SyntaxErrorException e) {
                handleError(filename, book, e);
                i--;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Book[] books = new Book[i];
        for (i = 0; i < validBooks.length && validBooks[i]!=null; i++) {
            books[i] = new Book(validBooks[i]);
        }
        return books;
    }

    /**
     * Method to handle the syntax errors
     * @param filename File in which syntax error was discovered
     * @param book book record
     * @param e Syntax error exception
     */
    private static void handleError(String filename, String book, SyntaxErrorException e) {
        BufferedWriter writer = FileWriterFactory.getSyntaxErrorFileWriter(filename);
        try {
            writer.write("Error: ");
            writer.write(e.getMessage());
            writer.write('\n');
            writer.write("Record: ");
            writer.write(book);
            writer.write('\n');
            writer.write('\n');
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method to check for syntax errors
     * @param bookDetails Details of books
     * @return valid book details
     * @throws TooFewFieldsException when encountering said error
     * @throws TooManyFieldsException when encountering said error
     * @throws MissingFieldException when encountering said error
     * @throws UnknownGenreException when encountering said error
     */
    private static String[] checkForSyntaxErrors(String[] bookDetails) throws TooFewFieldsException, TooManyFieldsException, MissingFieldException, UnknownGenreException {
        if(bookDetails.length<6)
            throw new TooFewFieldsException();
        if(bookDetails.length>6)
            throw new TooManyFieldsException();
        for (int i = 0; i < bookDetails.length; i++) {
            if(bookDetails[i] == null || bookDetails[i].isEmpty() || bookDetails[i].equals(" "))
                throw new MissingFieldException(fields[i]);
        }

        boolean flag = false;
        for (String genre: genres) {
            if (bookDetails[4].equals(genre)) {
                flag = true;
                break;
            }
        }
        if(!flag)
            throw new UnknownGenreException();
        return bookDetails;
    }
}
