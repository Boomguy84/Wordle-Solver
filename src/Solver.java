import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) throws IOException {
        //creates an ArrayList for the answers
        ArrayList wordList = new ArrayList();

        //populates the ArrayList
        populateList(wordList);

        //gets the user's guess and checks to see if it's valid and then stores it in a variable
        String guess = guessChecker();

        System.out.println(guess);

        wordRemover(wordList, 'b');

        System.out.println(wordList);



    }

    //populates an ArrayList of all the possible answers
    public static ArrayList populateList(ArrayList wordList) throws IOException {
        //opens the file and declares a scanner to iterate through the file
        File file = new File("wordle-nyt-answers-alphabetical.txt");
        Scanner inputFile = new Scanner(file);

        //read lines from the file until there aren't more
        while (inputFile.hasNext()) {
            wordList.add(inputFile.nextLine());
        }

        //closes the file and returns the ArrayList
        inputFile.close();
        return wordList;
    }

    //method to make sure the guess is valid
    public static String guessChecker() {
        //creates a String to store the users guess, a Scanner to get the input, and a boolean value as an exit condition
        String guess;
        boolean valid = false;
        Scanner input = new Scanner(System.in);

        //do while loop to make sure the guess is valid
        do {
            System.out.print("Enter your guess: ");
            guess = input.next();

            if (guess.length() != 5 || !guess.matches("[a-zA-Z]+")) {
                System.out.println("Invalid entry");
            } else {
                valid = true;
            }
        } while (!valid);

        return guess;
    }

    //method that removes any word that is the word of the day
    public static void wordRemover(ArrayList wordList, char character) {
        Iterator iterator = wordList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().toString().charAt(0) != character) {
                iterator.remove();
            }

        }
    }




}

