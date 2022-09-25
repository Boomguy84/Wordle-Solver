import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) throws IOException {
        //variable declarations
        boolean valid = false;

        //creates an ArrayList for the answers and a Scanner for user input
        ArrayList<String> wordList = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        //populates the ArrayList with all the words in the wordle database
        populateList(wordList);

        //loops the process 6 times so that it ends when wordle ends
        for (int j = 0; j < 6; j++) {
            //gets the user's guess and checks to see if it's valid and then stores it in a variable
            String guess = guessChecker();

            //asks the user for the data associated with each letter
            for (int i = 0; i < 5; i++) {

                //loops while the user gives invalid data
                String positionValue;
                do {
                    valid = false;
                    System.out.printf("Was letter number %d grey, yellow, or green: ", i + 1);
                    positionValue = input.next();
                    if (positionValue.equalsIgnoreCase("grey") || positionValue.equalsIgnoreCase("yellow") || positionValue.equalsIgnoreCase("green")) {
                        valid = true;
                    } else {
                        System.out.println("Invalid input");
                    }
                } while (!valid);

                //if statements which call the wordRemover method to remove incorrect answers based on user input
                if (positionValue.equalsIgnoreCase("green")) {
                    wordRemover(wordList, guess.toLowerCase().charAt(i), i, "green");
                } else if (positionValue.equalsIgnoreCase("yellow")) {
                    wordRemover(wordList, guess.toLowerCase().charAt(i), i, "yellow");
                } else {
                    wordRemover(wordList, guess.toLowerCase().charAt(i), i, "grey");
                }
            }

            //prints out the possible answers
            System.out.println("List of possible words: ");
            System.out.println(wordList);

            //exits the loop if there is only one possible word
            if (wordList.size() == 1) {
                break;
            }
        }

        //if the word is found it displays the word to the user, if not then it informs the user that the word could not be determined
        if (wordList.size() == 1) {
            System.out.printf("The word is: %s", wordList.get(0));
        } else {
            System.out.println("The word could not be determined");
        }

    }

    //populates an ArrayList of all the possible answers
    public static void populateList(ArrayList<String> wordList) throws IOException {
        //opens the file and declares a scanner to iterate through the file
        File file = new File("wordle-nyt-answers-alphabetical.txt");
        Scanner inputFile = new Scanner(file);

        //read lines from the file and adds them to the wordList
        while (inputFile.hasNext()) {
            wordList.add(inputFile.nextLine());
        }

        //closes the file
        inputFile.close();
    }

    //method to make sure the guess is 5 letters
    public static String guessChecker() {
        //creates a String to store the users guess, a Scanner to get the input, and a boolean value as an exit condition
        String guess;
        boolean valid = false;
        Scanner input = new Scanner(System.in);

        //do while loop to make sure the guess is 5 letters
        do {
            System.out.print("Enter your guess: ");
            guess = input.next();

            if (guess.length() != 5 || !guess.matches("[a-zA-Z]+")) {
                System.out.println("Invalid entry");
            } else {
                valid = true;
            }
        } while (!valid);

        //returns the users guess to be stored in a variable and used
        return guess;
    }

    //method that removes any word that is the word of the day
    public static void wordRemover(ArrayList<String> wordList, char character, int charPosition, String colour) {
        //creates a new iterator object to iterate through wordList
        Iterator<String> iterator = wordList.iterator();

        //if statements that remove incorrect words based off uer input
        if (charPosition >= 0 && colour.equalsIgnoreCase("green")) {
            while (iterator.hasNext()) {
                if (iterator.next().charAt(charPosition) != character) {
                    iterator.remove();
                }
            }
        } else if (colour.equalsIgnoreCase("yellow")) {
            while (iterator.hasNext()) {
                String currentWord = iterator.next();
                if (!currentWord.contains(character + "") || currentWord.charAt(charPosition) == character) {
                    iterator.remove();
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (iterator.next().charAt(charPosition) == character) {
                    iterator.remove();
                }
            }
        }
    }
}

