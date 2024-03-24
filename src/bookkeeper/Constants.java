package bookkeeper;


/**
 * @author Sanjay Bharathi
 * @author DINESH KUMAR
 * Assignment 2
 * Written by: Dinesh Kumar Gopinathan - 40273281, Sanjay Bharathi Subramanian - 40248572
 * 24 March 2024
 *
 * This class is used to store the constants used in the project
 */

public class Constants {


    /*
     * |",":| - Matches for ","
     * |(?=)| - Positive lookahead
     * |([^\"]*\"[^\"]*\")*| - Zero or more occurrence of the sub regex as below
     * |[^\"]*| - Any character not a double quote
     * |\"| - Double quote
     * |[^\"]*| - Not a double quote
     *          This sub regex matches sequence with non-double quote, double quote, non-double quote
     * |[^\"]*$| - Zero or more non-double quote characters till end of string
     */
    static final String csvSplitRegex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    /**
     * Directory where the data files are stored
     */
    static final String dataDirectory = "./data";
    /**
     * Directory where the output files are stored
     */
    static final String outputDirectory = "./output";
    /**
     * Directory where the output files for part 2 are stored
     */
    static final String part2OutputDirectory = "./part2Output";

    /**
     * File containing the names of the input files
     */
    static final String inputFileNames = dataDirectory + "/part1_input_file_names.txt";
    /**
     * File containing the names of the output files
     */
    static final String syntaxErrorFileName = outputDirectory + "/syntax_error_file.txt";
    /**
     * File containing the names of the output files
     */
    static final String semanticErrorFileName = part2OutputDirectory + "/semantic_error_file.txt";


    /**
     * Array containing the genres
     */
    static final String[] genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
    /**
     * Array containing the fields of a book
     */
    static final String[] fields = {"title", "authors", "price", "isbn", "genre", "year"};
    /**
     * Array containing the names of the files for each genre
     */
    static final String[] fileNameForGenres = {
            "Cartoons_Comics_Books.csv",
            "Hobbies_Collectibles_Books.csv",
            "Movies_TV.csv",
            "Music_Radio_Books.csv",
            "Nostalgia_Eclectic_Books.csv",
            "Old_Time_Radio.csv",
            "Sports_Sports_Memorabilia.csv",
            "Trains_Planes_Automobiles.csv"
    };

    /**
     * Array containing the names of the binary files for each genre
     */
    static final String[] binaryFileNames = {
            "Cartoons_Comics_Books.csv.ser",
            "Hobbies_Collectibles_Books.csv.ser",
            "Movies_TV.csv.ser",
            "Music_Radio_Books.csv.ser",
            "Nostalgia_Eclectic_Books.csv.ser",
            "Old_Time_Radio.csv.ser",
            "Sports_Sports_Memorabilia.csv.ser",
            "Trains_Planes_Automobiles.csv.ser"
    };

    static final int[] recordCount = new int[binaryFileNames.length];
    static final Book[][] deserializedBooks = new Book[Constants.binaryFileNames.length][];
}
