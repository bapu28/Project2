import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Akshat Shah
 * @version 9/22/2018
 *
 */
public class Parser {

    private Hash movieTable;
    private Hash reviewerTable;


    /**
     * 
     * @param size
     * @param file
     * @throws FileNotFoundException
     */
    public Parser(int size, String file) throws FileNotFoundException {
        movieTable = new Hash(size);
        reviewerTable = new Hash(size);
        this.loadFile(file);
    }


    /**
     * 
     * @param filename
     * @throws FileNotFoundException
     */
    private void loadFile(String filename) throws FileNotFoundException {
        Scanner cmd = null;
        cmd = new Scanner(new File(filename));
        this.cmdParser(cmd);
    }


    /**
     * 
     * @param scan
     */
    private void cmdParser(Scanner scan) {
        while (scan.hasNextLine()) {
            String temp = scan.nextLine();
            if (temp.equals("  ")) {
                temp = scan.nextLine();
            }
            temp = temp.trim();
            temp = temp.replaceAll("\\s++", " ");
            String[] array = temp.split(" ");

            if (array[0].matches("(.*)print(.*)")) {
                if (array[1].matches("(.*)hashtable(.*)")) {
                    if (array[2].matches("(.*)movie(.*)")) {
                        System.out.println("Movies: ");
                        for (int i = 0; i < movieTable.getSize(); i++) {
                            if (movieTable.getHashTable()[i] != null) {
                                if (!movieTable.getHashTable()[i].equals(
                                    "TOMBSTONE")) {
                                    System.out.println("|" + movieTable
                                        .getHashTable()[i] + "| " + Integer
                                            .toString(i));
                                }
                            }
                        }
                        System.out.println("Total Records: " + movieTable
                            .getCounter());
                    }
                    else if (array[2].matches("(.*)reviewer(.*)")) {
                        System.out.println("Reviewers: ");
                        for (int i = 0; i < reviewerTable.getSize(); i++) {
                            if (reviewerTable.getHashTable()[i] != null) {
                                if (!reviewerTable.getHashTable()[i].equals(
                                    "TOMBSTONE")) {
                                    System.out.println("|" + reviewerTable
                                        .getHashTable()[i] + "| " + Integer
                                            .toString(i));
                                }
                            }
                        }
                        System.out.println("Total Records: " + reviewerTable
                            .getCounter());
                    }
                }
                else if (array[1].matches("(.*)ratings(.*)")) {
                    System.out.println("There are no ratings in the database.");
                }

            }
            else if (array[0].matches("(.*)add(.*)")) {
                String name = "";
                int i = 1;
                while (i < array.length - 1) {
                    name += array[i];
                    name += " ";
                    i++;
                }
                name += array[i];

                int a = name.indexOf("<SEP>");
                String reviewerName = name.substring(0, a);
                int b = name.lastIndexOf("<SEP>");
                String movieName = name.substring(a + 5, b);
                int rating = Integer.parseInt(name.substring(b + 5));
                if (rating >= 1 && rating <= 10) {
                    if (reviewerTable.insert(reviewerName) && movieTable.insert(
                        movieName)) {
                        System.out.println("Rating added: " + "|" + reviewerName
                            + "|, |" + movieName + "|, " + rating);
                    }
                }
                else {
                    System.out.println("Bad Score " + "|" + rating + "|. "
                        + "Scores must be between 1 and 10.");
                }
            }
            else if (array[0].matches("(.*)delete(.*)")) {
                if (array[1].matches("(.*)movie(.*)")) {
                    String movieName = "";
                    int i = 2;
                    while (i < array.length - 1) {
                        movieName += array[i];
                        movieName += " ";
                        i++;
                    }
                    movieName += array[i];
                    if (movieTable.hashDelete(movieName)) {
                        System.out.println("|" + movieName + "| "
                            + "has been deleted from the Movie database.");
                    }
                    else {
                        System.out.println("|" + movieName + "| "
                            + "not deleted because it does not exist in the Movie database.");
                    }
                }
                else if (array[1].matches("(.*)reviewer(.*)")) {
                    String reviewerName = "";
                    int i = 2;
                    while (i < array.length - 1) {
                        reviewerName += array[i];
                        reviewerName += " ";
                        i++;
                    }
                    reviewerName += array[i];
                    if (reviewerTable.hashDelete(reviewerName)) {
                        System.out.println("|" + reviewerName + "| "
                            + "has been deleted from the Reviewer database.");
                    }
                    else {
                        System.out.println("|" + reviewerName + "| "
                            + "not deleted because it does not exist in the Reviewer database.");
                    }
                }

            }

        }

    }


    /**
     * 
     * @return
     */
    public Hash getMovieTable() {
        return movieTable;
    }


    /**
     * 
     * @return
     */
    public Hash getReviewerTable() {
        return reviewerTable;
    }

}
