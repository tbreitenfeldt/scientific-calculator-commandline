/**
 * @author TJ Breitenfeldt
 * course: CSCD320 Algorithms
 *
 * midterm project
*/


import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


/**
 * class InputUtils
 * provides method utilities for handling user input
*/
public class InputUtils {

    /**
     * method inputFilter
     * Checks a given string to see if it is only numbers, and if it contains numbers 2-9 inclusive
     * @param String input - the string to check 
     * @return boolean true if the string has a correct format, false if it is not 
    */
    public static boolean filterInput(final String input) throws IllegalArgumentException {
        if (input == null) {
            throw new IllegalArgumentException("input can not be null");
        }  //end if 
        if (input.isEmpty()) {
            throw new IllegalArgumentException("input can not be empty");
        }  //end if 

        return (InputUtils.isNumbers(input) && InputUtils.isInRange(input));
    }  //end method filter(String)

    /**
     * helper method isNumbers
     * checks a string to see if it is only numbers 
     * @param String input - the string to check 
     * @return boolean - returns true if the string contains only numbers, false if not.
    */
    private static boolean isNumbers(final String input) {
        int inputLength = input.length();

        for (int index = 0; index < inputLength; index++) {
            char currentValue = input.charAt(index);

            if ( !Character.isDigit(currentValue)) {
                return false;
            }  //end if 
        }  //end for loop 

        return true;
    }  //end method isNumbers(String)

    /**
     * helper method isInRange
     * checks if the given string contains only numbers between 2 and 9 inclusive
     * @param String input - the input to check
     * @return boolean - returns true if the string only contains numbers between 2 and 9 inclusive and false if not.
    */
    private static boolean isInRange(final String input) {
        int inputLength = input.length();

        for (int index = 0; index < inputLength; index++) {
            char currentInput = input.charAt(index);
            
            if (currentInput == '0' || currentInput == '1') {
                return false;
            }  //end if
        }  //end for loop 

        return true;
    }  //end method isInRange(String)

    /**
     * method printWords
     * compare the input possibilities to the actual words stored in the dictionary, and print the matches
     * @param DictPrefixTree - the dictionary that stores the dictionary.txt file
     * @param LinkedList<String> - the user input word possibilities for the string of numbers given 
    */
    public static void printWords(final DictPrefixTree dictionary, final LinkedList<String> inputPossibilities) {
        for (int index = 0; index < inputPossibilities.size(); index++) {
            String word = inputPossibilities.get(index);

            if (dictionary.isWord(word)) {
                System.out.println(word);
            }  //end if 
        }  //end for loop 
    }  //end method printWords(DictPrefixTree, LinkedList<String>)

    /**
     * method buildDictionary
     * take a prefix tree and a text file name for the dictionary itself, and build the DictPrefixTree structure
     * @param DictPrefixTree - the prefix tree data structure to store the contents of the given text file
     * @param String - the name of the dictionary file 
    */
    public static void buildDictionary(final DictPrefixTree tree, final String filename) throws IOException {
        File fo = new File(filename);
        Scanner fin = new Scanner(fo);

        while (fin.hasNextLine()) {
            String line = fin.nextLine();
            String[] splitLine = line.split(",");

            tree.insert(splitLine[0]);
        }  //end while loop 
    }  //end method buildDictionary(DictPrefixTree, String)

}  //end class InputUtils
