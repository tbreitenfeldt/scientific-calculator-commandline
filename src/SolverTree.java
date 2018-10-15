/**
 * @author TJ Breitenfeldt
 * course: CSCD320 Algorithms
 *
 * midterm project
*/

import java.util.Hashtable;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * SolverTree Class 
 * This class is used to contain the number to letter association based on the 9 digit keypad on old phones.
 * I misunderstood how Tony wanted us to build the SolverTree because I could not see the picture do to my vision impairment
 * so I created a linked list implementation to solve this problem.
 * Tony looked at it, and said it was okay.
*/
public class SolverTree {


    /**
     * inner class Node 
     * This node class is used to store the associated letters to the given number
    */
    private class Node {

        private Integer value;
        private String[] letters;
        private Node next;

        //constructor 
        public Node(final Integer value, final String[] letters, final Node next) {
            this.value = value;
            this.letters = letters;
            this.next = next;
        }  //end constructor Node(int, String[], Node)

        //constructor 
        public Node(final Integer value, final String[] letters) {
            this(value, letters, null);
        }  //end constructor Node(Integer, String[])

        //constructor
        public Node() {
            this(null, null, null);
        }  //end constructor Node

    }  //end inner class Node


    private Node head;
    private int size;

    //constructor
    public SolverTree() {
        this.head = new Node();
    }  //end constructor SolverTree 

    /**
     * method buildTree
     * insert data into the SolverTree given a String input
     * @param String input - the input string given by the user
    */
    public void buildTree(final String input) throws IllegalArgumentException{
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input can not be empty");
        }  //end if 

        Hashtable<Integer, String[]> table = this.createTable();
        Node currentNode = this.head;
        int inputLength = input.length();

        for (int inputIndex = 0; inputIndex < inputLength; inputIndex++) {
            int currentInputValue = Character.getNumericValue(input.charAt(inputIndex));

            currentNode.next = new Node(currentInputValue, table.get(currentInputValue));
            this.size++;
            currentNode = currentNode.next;
        }  //end for loop 
    }  //end method buildTree(String)

    /**
     * helper method buildTable
     * Builds the table of numbers and associated letters
     * by looping through a list of all 26 letters, and paring 2 - 9 with the correct letters.
     * @return Hashtable<Integer, String> the Hashtable to be created 
    */
    private Hashtable<Integer, String[]> createTable() {
        String[] lettersArray = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Hashtable<Integer, String[]> table = new Hashtable<Integer, String[]>();

        for (int key = 2; key <= 9; key++) {
            String[] value = lettersArray[key - 2].split("");
            
            table.put(key, value);
        }  //end for loop

        return table;
    }  //end helper method buildTable

    /**
     * method getPossibilities
     * return a LinkedList of all combonations of the different patterns that can be created 
     * with the letters of the given string of numbers
     * @return LinkedList<String> - the LinkedList of possible permutations
    */
    public LinkedList<String> getPossibilities() {
        LinkedList<String> results = new LinkedList<String>();
        String[] pattern = new String[this.size];

        this.getPatterns(this.head.next, 0, pattern, results);
        return results;
    }  //end method getPossibilities()

    /**
     * helper method getPatterns
     * get the letters for the current digit, loop through them, and on every iteration
     * add the letter to the pattern array, and make the recursive call to then increase the index for the pattern array.
     * so that the first letter of every letters sequence is added, then the recursive call hits the base case, and the next letter in the sequence is then added for the last call.
     * @param Node - the current node used to keep track of what digit of the user input the program  is on
     * @param int - the pattern array index 
     * String[] - the pattern array used to store each pattern as it is constructed 
     * @param LinkedList<String> - the list to store all of the patterns in
    */
    private void getPatterns(Node currentNode, int patternIndex, String[] pattern, LinkedList<String> results) {
        if (patternIndex == this.size) {
            results.add(String.join("", pattern));
        } else {
            String[] letters = currentNode.letters;

            for (int lettersIndex = 0; lettersIndex < letters.length; lettersIndex++) {
                pattern[patternIndex] = letters[lettersIndex];
                getPatterns(currentNode.next, patternIndex+1, pattern, results);
            }  //end for loop 
     }  //end else 
    }  //end helper method getPatterns(Node, LinkedList<String>)

    @Override
    public String toString() {
        String text = "";

        if (this.head.next != null) {
            for (Node currentNode = this.head.next; currentNode != null; currentNode = currentNode.next) {
                text += currentNode.value + ": " + Arrays.toString(currentNode.letters) + "\n";
            }  //end for loop 
        }  //end if 

        return text;
    }  //end method toString()

}  //end class SolverTree