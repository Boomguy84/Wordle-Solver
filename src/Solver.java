import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) throws IOException {
        String positionValue = "null";
        boolean valid;

        //creates an ArrayList for the answers
        ArrayList wordList = new ArrayList();
        Scanner input = new Scanner(System.in);

        //populates the ArrayList
        populateList(wordList);

        for (int j = 0; j < 6; j++) {
            //gets the user's guess and checks to see if it's valid and then stores it in a variable
            String guess = guessChecker();


            for (int i = 0; i < 5; i++) {
                valid = false;
                while (!valid) {
                    System.out.printf("Was letter number %d black, yellow, or green: ", i + 1);
                    positionValue = input.next();
                    if (positionValue.equalsIgnoreCase("black") || positionValue.equalsIgnoreCase("yellow") || positionValue.equalsIgnoreCase("green")) {
                        valid = true;
                    } else {
                        System.out.println("Invalid input");
                    }
                }
                if (positionValue.equalsIgnoreCase("green")) {
                    wordRemover(wordList, guess.charAt(i), i, "green");
                } else if (positionValue.equalsIgnoreCase("yellow")) {
                    wordRemover(wordList, guess.charAt(i), i, "yellow");
                } else {
                    wordRemover(wordList, guess.charAt(i), i, "black");
                }

            }

            System.out.println("List of possible words: ");
            System.out.println(wordList);

            if (wordList.size() == 1) {
                break;
            }
        }

        if (wordList.size() == 1) {
            System.out.printf("The word is: %s", wordList.get(0));
        } else {
            System.out.println("The word could not be determined");
        }

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
    public static void wordRemover(ArrayList wordList, char character, int charPosition, String colour) {
        Iterator iterator = wordList.iterator();
        if (charPosition >= 0 && colour.equalsIgnoreCase("green")) {
            while (iterator.hasNext()) {
                if (iterator.next().toString().charAt(charPosition) != character) {
                    iterator.remove();
                }

            }
        } else if (colour.equalsIgnoreCase("yellow")) {
            while (iterator.hasNext()) {
                String currentWord = iterator.next().toString();
                if (!currentWord.contains(character + "") || currentWord.charAt(charPosition) == character) {
                    iterator.remove();
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (iterator.next().toString().charAt(charPosition) == character) {
                    iterator.remove();
                }
            }
        }
    }
}

