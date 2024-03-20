package bookkeeper;

import java.io.*;

import static bookkeeper.Constants.*;

/**
 * @author Sanjay Bharathi
 */

public class FileWriterFactory {
    private static BufferedWriter syntaxErrorFileWriter = null;
    private static PrintWriter semanticErrorFileWriter = null;
    private static final BufferedWriter[] genreBasedFileWriter = new BufferedWriter[8];
    private static String currentFileName;

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

    public static void closeSyntaxErrorFileWriter(){
        try {
            syntaxErrorFileWriter.close();
            syntaxErrorFileWriter = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closeSemanticErrorFileWriter(){
        semanticErrorFileWriter.close();
        semanticErrorFileWriter = null;
    }

    public static BufferedWriter getGenreBasedFileWriter(String genreName){
        int index = indexOf(genres, genreName);
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

    public static void closeAllGenreBasedFileWriters(){
        for (BufferedWriter w :
                genreBasedFileWriter) {
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static int indexOf(String[] array, String searchString) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(searchString)) {
                return i;
            }
        }
        return -1;
    }
}
