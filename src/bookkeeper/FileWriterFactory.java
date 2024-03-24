package bookkeeper;

import java.io.*;

import static bookkeeper.Constants.*;
/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 *
 * This class is used to create the file writers
 */

public class FileWriterFactory {
    private static BufferedWriter syntaxErrorFileWriter = null;
    private static PrintWriter semanticErrorFileWriter = null;
    private static final BufferedWriter[] genreBasedFileWriter = new BufferedWriter[8];
    private static String currentFileName;

    /**
     * Method to write the syntax error
     * @param fileName - name of the file
     * @return - the file writer
     */
    public static BufferedWriter getSyntaxErrorFileWriter(String fileName){
        if(syntaxErrorFileWriter == null){
            try {
                syntaxErrorFileWriter = new BufferedWriter(new FileWriter(syntaxErrorFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!fileName.equals(currentFileName)){
            currentFileName = fileName;
            try {
                syntaxErrorFileWriter.write("syntax error in file: " + fileName);
                syntaxErrorFileWriter.write('\n');
                syntaxErrorFileWriter.write("====================");
                syntaxErrorFileWriter.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return syntaxErrorFileWriter;
    }

    /**
     * Method to write the semantic error
     * @param fileName - name of the file
     * @return - the file writer
     */
    public static PrintWriter getSemanticErrorFileWriter(String fileName){
        if(semanticErrorFileWriter == null){
            try {
                semanticErrorFileWriter = new PrintWriter(semanticErrorFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!fileName.equals(currentFileName)){
            currentFileName = fileName;
            semanticErrorFileWriter.println(" ");
            semanticErrorFileWriter.println("Semantic error in file: " + fileName);
            semanticErrorFileWriter.println("====================");
        }
        return semanticErrorFileWriter;
    }

    /**
     * Method to close the syntax error file writer
     */
    public static void closeSyntaxErrorFileWriter(){
        try {
            syntaxErrorFileWriter.close();
            syntaxErrorFileWriter = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to close the semantic error file writer
     */
    public static void closeSemanticErrorFileWriter(){
        semanticErrorFileWriter.close();
        semanticErrorFileWriter = null;
    }

    /**
     * Method to get the genre based file writer
     * @param genreName - name of the genre
     * @return - the file writer
     */
    public static BufferedWriter getGenreBasedFileWriter(String genreName){
        int index = Utils.indexOf(genres, genreName);
        if(genreBasedFileWriter[index] == null){
            try {
                genreBasedFileWriter[index] =
                        new BufferedWriter(new FileWriter(outputDirectory + File.separator + fileNameForGenres[index]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return genreBasedFileWriter[index];
    }

    /**
     * Method to close all the genre based file writers
     */
    public static void closeAllGenreBasedFileWriters(){
        for (BufferedWriter w :
                genreBasedFileWriter) {
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        currentFileName = null;
    }


    /**
     * Method to get the index of the genre
     * @param array - array of genres
     * @param searchString - the genre name
     * @return - the index of the genre
     */
    public static int indexOf(String[] array, String searchString) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(searchString)) {
                return i;
            }
        }
        return -1;
    }
}
