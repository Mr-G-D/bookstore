package bookkeeper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static bookkeeper.Constants.syntaxErrorFileName;

/**
 * @author Sanjay Bharathi
 */

public class ErrorFileWriterFactory {
    private static BufferedWriter syntaxErrorFileWriter = null;
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

    public static void closeSyntaxErrorFileWriter(){
        try {
            syntaxErrorFileWriter.close();
            syntaxErrorFileWriter = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
