/**
 * Names: Jimmy Liu and Joshua Liu
 * Date: Feb 26, 2021
 * Teacher: Ms.Krasteva
 * Description: This class will load and print statistcs regardings William Lyon Mackenzie's competitive programmers with multiple arrays
 */

import java.io.*;
import java.util.*;

/**
 * This class uses parallel arrays to store user data
 */
public class Main {
    /** This array stores the users' usernames */
    public static String username[];

    /** This array stores the users' typing speed in words per minute */
    public static int wpm[];

    /** This array stores the number of problems that each user solved */
    public static int problems_solved[];

    /** This arrays stores the users' grades */
    public static int grade[];

    /** This array stores the users' actual full names */
    public static String realName[];

    /** This String stores the name of the text file to read data from */
    private static String fileName;

    /** This scanner is used to read user input */
    private static Scanner sc;

    /**
     * This is the program main method
     */
    public static void main(String[] args) throws IOException {
        sc = new Scanner(System.in);
        mainMenu();
    }

    /**
     * This method reads a string input from the user asking for the name of the
     * file to read from
     */
    public static void getFileName() {
        System.out.println("Please enter the name of the file to read from. (Recommended file is \"wlmac.txt\")");
        fileName = sc.nextLine();
    }

    /**
     * Reads data from the file.
     * 
     * @return true if the file reading is a success; false if there is an error
     *         with the data format
     */
    public static boolean readFile() throws IOException {
        BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(fileName)));
        int numberOfUsers = 0;
        try {
            numberOfUsers = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            return false;
        }

        username = new String[numberOfUsers];
        wpm = new int[numberOfUsers];
        problems_solved = new int[numberOfUsers];
        grade = new int[numberOfUsers];
        realName = new String[numberOfUsers];

        for (int user = 0; user < numberOfUsers; user++) {
            username[user] = br.readLine();

            try {
                String wpmstr = br.readLine();
                wpm[user] = Integer.parseInt(wpmstr);

                String problemstr = br.readLine();
                problems_solved[user] = Integer.parseInt(problemstr);

                String gradestr = br.readLine();
                grade[user] = Integer.parseInt(gradestr);

                realName[user] = br.readLine();
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method rates a certain user based on the number of problems solved and
     * words per minute typing speed
     * 
     * @param idx This integer indicates the index of the user to rate
     * @return An integer representing the rating given to the user
     */
    public static int rate(int idx) {
        return problems_solved[idx] + wpm[idx] / 3;
    }

    /**
     * This method displays the username of the user with the highest rating in the
     * array
     */
    public static void display() {
        int highestRating = -1, userIdx = -1;
        for (int user = 0; user < username.length; user++) {
            int rating = rate(user);
            if (rating > highestRating) {
                highestRating = rating;
                userIdx = user;
            }
        }
        if (highestRating == -1) {
            System.out.println("There are no users in the data file");
        } else {
            System.out.println("The highest rated user is: " + username[userIdx]);
        }
    }

    /**
     * This method displays all the users' information in a table
     */
    public static void displayAll() {
        System.out.printf("%-22s| %-4s| %-20s| %-7s| %-10s\n", "Username", "WPM", "Problems Solved", "Grade",
                "Real Name");
        System.out.println("--------------------------------------------------------------------------");
        for (int i = 0; i < username.length; i++) {
            System.out.printf("%-22s| %-4d| %-20d| %-7d| %-10s\n", username[i], wpm[i], problems_solved[i], grade[i],
                    realName[i]);
        }
    }

    /**
     * This method is used to control the flow of the program and ask for choice
     * user inputs
     */
    public static void mainMenu() throws IOException {
        getFileName();

        try {
            if (!readFile()) {
                System.out.println("Error: file data is in an incorrect format");
                return;
            }
        } catch (IOException e) {
            System.out.println(
                    "\nError: File specified by user (\"" + fileName + "\") was not found in the project folder");
            System.out.println("Please rerun this program.\n");
            return;
        }

        System.out.println(
                "Type in 'H' to view the highest rated user. Type in any other character to view all users in the file:");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("H")) {
            display();
        } else {
            displayAll();
        }
    }
}
