package bookkeeper;

import bookkeeper.exceptions.SemanticErrorException;
import bookkeeper.exceptions.SyntaxErrorException;
import bookkeeper.exceptions.semantic.BadIsbn10Exception;
import bookkeeper.exceptions.semantic.BadIsbn13Exception;
import bookkeeper.exceptions.semantic.BadPriceException;
import bookkeeper.exceptions.semantic.BadYearException;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author DINESH KUMAR
 */

public class Utils {
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

    public static boolean checkPrice(double price){
        return price > 0;
    }

    public static boolean checkYear(int year){
        return (year > 1995 && year < 2024);
    }

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

    public static void handleSemanticError(String filename, String book, SemanticErrorException e) {
        PrintWriter writer = FileWriterFactory.getSemanticErrorFileWriter(filename);
        writer.print("Error: ");
        writer.println(e.getMessage());
        writer.print("Record: ");
        writer.println(book);
//            writer.write('\n');
    }

//    public static void main(String[] args) {
//        checkISBN10("0306406152");
//    }
}
