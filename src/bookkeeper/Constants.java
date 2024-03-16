package bookkeeper;

/**
 * @author Sanjay Bharathi
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

    static final String dataDirectory = "./data";
    static final String outputDirectory = "./output";
    static final String part2OutputDirectory = "./part2Output";

    static final String inputFileNames = dataDirectory + "/part1_input_file_names.txt";
    static final String syntaxErrorFileName = outputDirectory + "/syntax_error_file.txt";
    static final String semanticErrorFileName = part2OutputDirectory + "/semantic_error_file.txt";


    static final String[] genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
    static final String[] fields = {"title", "authors", "price", "isbn", "genre", "year"};
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
}
