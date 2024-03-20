package bookkeeper;

import bookkeeper.exceptions.SemanticErrorException;
import bookkeeper.exceptions.SyntaxErrorException;
import bookkeeper.exceptions.semantic.BadIsbn10Exception;
import bookkeeper.exceptions.semantic.BadIsbn13Exception;
import bookkeeper.exceptions.semantic.BadPriceException;
import bookkeeper.exceptions.semantic.BadYearException;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

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
//        writer.close();
    }

    public static String displayMainMenu(String str){
        System.out.println("-----------------------------------------------------");
//        System.out.println("");
        System.out.println("                     Main Menu                       ");
//        System.out.println("");
        System.out.println("-----------------------------------------------------");
        System.out.println("v View the selected file: " + str);
        System.out.println("s Select a file to view");
        System.out.println("x Exit");
        System.out.println("-----------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Your Choice: ");
        Scanner input = new Scanner(System.in);
        return (input.nextLine());
    }

    public static void displaySubMenu() throws IOException, ClassNotFoundException {
        System.out.println("-----------------------------------------------------");
//        System.out.println("");
        System.out.println("                   File Sub Menu                     ");
//        System.out.println("");
        System.out.println("-----------------------------------------------------");
        
        String[] files = Constants.binaryFileNames;

        for (int i = 0; i < files.length; i++) {
            String f = String.format("%-5d %-40s %5s", i+1, files[i], "(" + findRecords(files[i]) + " records)");
            System.out.println(f);

        }
        System.out.println("-----------------------------------------------------");
        System.out.println("");
        System.out.print("Enter Your Choice: ");
        Scanner input = new Scanner(System.in);
        int i = input.nextInt();
        navigate(files[i-1]);
    }


    public static void navigate(String s) throws IOException, ClassNotFoundException {
        String str = displayMainMenu(s);
        if(Objects.equals(str, "v")){
            System.out.println("v");
        } else if (Objects.equals(str, "s")) {
            displaySubMenu();
        }

    }

    public static int findRecords(String str) throws IOException, ClassNotFoundException {
        ObjectInputStream file = new ObjectInputStream(new FileInputStream(Constants.part2OutputDirectory + File.separator + str));

        int count = 0;
        while (file.readObject() != null){
            count++;
        }

        return count;
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        displaySubMenu();
    }
}
