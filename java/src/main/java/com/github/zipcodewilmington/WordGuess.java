package com.github.zipcodewilmington;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
// Collin Beck 

// Phase 1: Create a simple word guess game that prompts the user to guess a letter 
// and displays the guessed letter back to the user. The game should continue until 
// the user types "quit" to exit the game. 

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

class Hangman {
    private String word;
    private Set<Character> guessedLetters;
    private int maxAttempts;
    private int wrongGuesses;

    public Hangman(String word) {
        this.word = word.toUpperCase();
        this.guessedLetters = new HashSet<>();
        this.maxAttempts = 6;
        this.wrongGuesses = 0;
    }

    public String displayWord() {
        StringBuilder display = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(c)) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }
        return display.toString().trim();
    }

    public boolean guess(char letter) {
        letter = Character.toUpperCase(letter);
        if (guessedLetters.contains(letter)) {
            return word.indexOf(letter) != -1;
        }
        guessedLetters.add(letter);
        if (word.indexOf(letter) == -1) {
            wrongGuesses++;
            return false;
        }
        return true;
    }

    public String getSecretWord() {
        return word;
    }

    public boolean isWordGuessed() {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) return false;
        }
        return true;
    }

    public boolean isGameOver() {
        return wrongGuesses >= maxAttempts;
    }

    public int getAttemptsLeft() {
        return maxAttempts - wrongGuesses;
    }

    public Set<Character> getGuessedLetters() {
        Set<Character> guessedLetters = new HashSet<>();
        for (char c : this.guessedLetters) { 
            if (word.indexOf(c) == -1) {
                guessedLetters.add(c);
            }
        }
        return guessedLetters;
    }
}

public class WordGuess {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Word Guess Game!");

            String[] words = {"soccer", "zipcode", "voltage", "tennis", "hockey", "queen", "programming" };
            Random random = new Random();
            boolean keepPlaying = true;

            while (keepPlaying) {
                String randomWord = words[random.nextInt(words.length)];
                Hangman game = new Hangman(randomWord);

                System.out.println("\nNew game! Guess the word!");
                System.out.println(game.displayWord());

                while (true) {
                    System.out.println("\nAttempts left: " + game.getAttemptsLeft());
                    System.out.print("Guess a letter (or 'quit'): ");
                    String input = scanner.nextLine();

                    if (input.equalsIgnoreCase("quit")) {
                        System.out.println("Thanks for playing! ");
                        keepPlaying = false;
                        break;
                    }

                    if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                        System.out.println("Please enter a single letter!");
                        continue;
                    }

                    boolean correct = game.guess(input.charAt(0));
                    System.out.println(correct ? "Great guess! " : "Wrong! ");
                    System.out.println(game.displayWord());

                    if (!game.getGuessedLetters().isEmpty()) {
                        System.out.println("Wrong guesses: " + game.getGuessedLetters());
                    }

                    if (game.isWordGuessed()) {
                        System.out.println(" You guessed the word!");
                        break;
                    }
                    if (game.isGameOver()) {
                        System.out.println("Game over! The word was: " + game.getSecretWord());
                        break;
                    }

                }

                if (keepPlaying) {
                    System.out.print("\nPlay again? (yes/no): ");
                    if (scanner.nextLine().equalsIgnoreCase("no")) {
                        System.out.println("Thanks for playing! ");
                        keepPlaying = false;
                    }
                }
            }
        }
    }
}

