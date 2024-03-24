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
     * @throws ClassNotFoundException - exception thrown in case of a class not found error
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Hello world!");
        do_part1();
        do_part2();
        do_part3();
    }

    /**
     * Method to accomplish part 1
     * @throws FileNotFoundException when the file is not found
     */
    static void do_part1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFileNames));
        int count = scanner.nextInt();
        scanner.nextLine();
        Book[][] books = new Book[count][];
        for (int i = 0; i < count; i++) {
            String filename = scanner.nextLine();
            books[i] = Utils.readIndividualFile(filename);
        }
        FileWriterFactory.closeSyntaxErrorFileWriter();
        FileWriterFactory.closeAllGenreBasedFileWriters();
    }


    /**
     * Method to accomplish part 2
     * @throws IOException when reading or writing files
     */
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

    /**
     * Method to process the files for part 3
     * @throws IOException - exception thrown in case of an IO error
     * @throws ClassNotFoundException - exception thrown in case of a class not found error
     */
    public static void do_part3() throws IOException, ClassNotFoundException {
        Utils.fillBooks();
        Utils.navigate(Constants.binaryFileNames[0]);
    }
}

