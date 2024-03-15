package bookkeeper;

/**
 * @author DINESH KUMAR
 */

public class Utils {
    public static void checkForSemantics(String[] bookName){

    }

    public static boolean checkPrice(int price){
        return price > 0;
    }

    public static boolean checkYear(int year){
        return (year > 1995 && year < 2024);
    }

    public static void checkISBN13(String isbn){

    }
}
