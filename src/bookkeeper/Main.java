package bookkeeper;

import bookkeeper.exceptions.*;
import bookkeeper.exceptions.syntax.MissingFieldException;
import bookkeeper.exceptions.syntax.TooFewFieldsException;
import bookkeeper.exceptions.syntax.TooManyFieldsException;
import bookkeeper.exceptions.syntax.UnknownGenreException;

import  static bookkeeper.Constants.*;

import java.io.*;
import java.util.Scanner;

/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 *
 *  This is the main class which reads the input files and processes them
 */


public class Main {
    /**
     * Main method to read the input files and process them
     * @param args - command line arguments
     * @throws IOException - exception thrown in case of an IO error
     * @throws SemanticErrorException - exception thrown in case of a semantic error
     * @throws ClassNotFoundException - exception thrown in case of a class not found error
     */
    public static void main(String[] args) throws IOException, SemanticErrorException, ClassNotFoundException {
        System.out.println("Hello world!");
        do_part1();
        do_part2();
        do_part3();
    }
    static void do_part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFileNames));
        int count = scanner.nextInt();
        scanner.nextLine();
        Book[][] books = new Book[count][];
        for (int i = 0; i < count; i++) {
            String filename = scanner.nextLine();
            books[i] = readIndividualFile(filename);
        }
        FileWriterFactory.closeSyntaxErrorFileWriter();
        FileWriterFactory.closeAllGenreBasedFileWriters();
    }

    private static Book[] readIndividualFile(String filename) throws FileNotFoundException {
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


    public static void do_part2() throws IOException {
        int genreItr = 0;
        for (String genre:
             fileNameForGenres) {
            Book[] books = new Book[100000];
            int bookItr = 0;
            Scanner fileName = new Scanner(new File(outputDirectory + File.separator + genre));
            while(fileName.hasNext()){
                String b = fileName.nextLine();
                String[] book = b.split(csvSplitRegex);
                try {
                    Utils.checkForSemantics(book);
                    books[bookItr++] = new Book(book);
                }catch (SemanticErrorException e){
                    Utils.handleSemanticError(genre, b, e);
                }
            }
            recordCount[genreItr++] = bookItr;

            try{
                ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(part2OutputDirectory + File.separator + genre + ".ser"));
                for (Book b:
                        books){
                    if(b!=null)
                        objectOutput.writeObject(b);
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        FileWriterFactory.closeSemanticErrorFileWriter();
    }

    public static void do_part3() throws IOException, ClassNotFoundException {
        Utils.fillBooks();
        Utils.navigate(Constants.binaryFileNames[0]);
    }
}

