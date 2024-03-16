package bookkeeper;

import bookkeeper.exceptions.SemanticErrorException;
import bookkeeper.exceptions.semantic.BadIsbn10Exception;
import bookkeeper.exceptions.semantic.BadIsbn13Exception;
import bookkeeper.exceptions.semantic.BadPriceException;
import bookkeeper.exceptions.semantic.BadYearException;

/**
 * @author DINESH KUMAR
 */

public class Utils {
    public static void checkForSemantics(String[] bookName) throws SemanticErrorException {
        if(checkPrice(Integer.parseInt(bookName[3]))){
            throw new BadPriceException();
        }
        if (checkYear(Integer.parseInt(bookName[4]))){
            throw new BadYearException();
        }
        if(bookName[3].length() == 10){
            if (checkISBN10(bookName[3])){
                throw new BadIsbn10Exception();
            }
        } else {
            if (checkISBN13(bookName[3])){
                throw new BadIsbn13Exception();
            }
        }
    }

    public static boolean checkPrice(int price){
        return price > 0;
    }

    public static boolean checkYear(int year){
        return (year > 1995 && year < 2024);
    }

    public static boolean checkISBN10(String isbn){
        int num = Integer.parseInt(isbn);
        int x = 1;
        int sum = 0;
        while (num > 0){
            int digit = num % 10;
            num = num / 10;
            sum += digit * x++;
        }
        return sum % 11 == 0;
    }

    public static boolean checkISBN13(String isbn){
        int num = Integer.parseInt(isbn);
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            int digit = num % 10;
            num = num / 10;
            if (i % 2 == 0){
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }
        return sum % 10 == 0;
    }

//    public static void main(String[] args) {
//        checkISBN10("0306406152");
//    }
}
