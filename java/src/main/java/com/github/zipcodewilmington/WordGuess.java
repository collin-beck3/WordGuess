package com.github.zipcodewilmington;

import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
// Collin Beck 

// Phase 1: Create a simple word guess game that prompts the user to guess a letter 
// and displays the guessed letter back to the user. The game should continue until 
// the user types "quit" to exit the game. 

public class WordGuess {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     System.out.println("Welcome to the Word Guess Game!");

     while (true) { 
         System.out.print("Guess a letter (or type 'quit' to exit): ");
         String guess = scanner.nextLine();
         if (guess.equalsIgnoreCase("quit")) {
             System.out.println("Thanks for playing!");
             scanner.close();
             break;
         }
         System.out.println("You guessed: " + guess);
     }
    }
}
