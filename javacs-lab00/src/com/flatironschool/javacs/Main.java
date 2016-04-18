package com.flatironschool.javacs;

import java.util.Scanner;

public class Main {

    // Class constants to indicate the size of the gameboard
    // R_SIZE is Q_SIZE incremented by 1 to be compatible with
    // for loops and such
    final static int Q_SIZE = 20 + 1;


    public static void main(String[] args) {
        // write your code here
        String questions[] = new String[Q_SIZE];
        questions = initialize(questions);
        String yesNo = " Input Y or N: ";
        Scanner sc = new Scanner(System.in);


        // Main loop to play the game
        String response;
        do {
            System.out.println("");
            int index = 1;
            boolean stillPlaying = true;
            boolean isProposal;

            while(index < Q_SIZE && stillPlaying) {

                // Print out the questions to the player
                if(questions[2*index] == null || Q_SIZE < 2*index) {
                    isProposal = true;
                    System.out.print("Is it ");
                } else {
                    isProposal = false;
                }
                System.out.print(questions[index] + yesNo);
                // Get the players response
                String answer = sc.nextLine();

                // End of path tree if it's a proposal
                if (isProposal) {
                    stillPlaying = false;

                    // The computer wins here
                    if (boolAnswer(answer)) {
                        System.out.println("I won!");
                    }
                    // The computer doesn't know whats happenning here, asks a new question
                    else {
                        questions = storeNewQuestion(questions, index, sc);
                    }
                }
                // games not over, update the index
                else {
                    index = 2*index;
                    if(!boolAnswer(answer)) {
                        index++;
                    }
                }
                // Continue another time around

                // debugging
                //dump(questions);
            }

            // play the game again
            System.out.print("Would you like to play again?" + yesNo);
            response = sc.nextLine();

        } while (boolAnswer(response));

        System.out.println("Thanks for playing!");
    }

    // Sets the first three questions for the start of the game
    public static String[] initialize(String[] questions) {
        questions[1] = "Is it alive?";
        questions[2] = "Is it a horse?";
        questions[3] = "Is it a lacrosse stick?";
        return questions;
    }

    // Function to print contents of the questions array to the console
    public static void dump(String[] questions) {

        System.out.println("\nHere are the current questions in the form Index:(Left,Right)");

        for(int i = 1; i < Q_SIZE; i++) {
            int left = 2*i;
            int right = 2*i + 1;
            if (questions[i] != null) {
                System.out.println(i + ":(" + left + "," + right + "): " + questions[i]);
            }
        }
        System.out.println();
    }

    // Asks the player a new question about an unknown noun to add to the database.
    public static String[] storeNewQuestion(String[] questions, int index, Scanner sc) {
        System.out.println("What were you thinking of?");
        String noun  = sc.nextLine();

        System.out.println("What question should I ask such that if the answer is Yes, I could propose " + noun +"?");
        String newQ = sc.nextLine();

        String oldProposal = questions[index];

        questions[index] = newQ;
        questions[2*index] = noun;
        questions[2*index + 1] = oldProposal;
        return questions;
    }

    // Converts a yes or no answer to a boolean. Yes == true, No == false, defaults to false for a bad input
    public static boolean boolAnswer(String input) {
        // respond true if the answer is yes, false if anything else.
        if (input.length() == 0) {
            return false;
        }

        char in = input.charAt(0);
        if (in == 'y' || in == 'Y') {
            return true;
        }
        return false;
    }
}
