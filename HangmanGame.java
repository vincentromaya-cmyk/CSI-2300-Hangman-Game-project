/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author vince
 */

package hangman;

import java.util.HashSet;
import java.util.Set;

public class HangmanGame {

    private final String secretWord;
    private final Set<Character> guessedLetters;
    private int incorrectGuesses;

    // Creates a new game with a word at random.
    public HangmanGame(String word) {
        secretWord = word.toLowerCase();
        guessedLetters = new HashSet<>();
        incorrectGuesses = 0;
    }

    // Checks the player's guess.
    public boolean guessLetter(char letter) {
        letter = Character.toLowerCase(letter);

        if (!Character.isLetter(letter)
                || guessedLetters.contains(letter)) {
            return false;
        }

        guessedLetters.add(letter);

        if (secretWord.indexOf(letter) == -1) {
            incorrectGuesses++;
        }

        return true;
    }

    // Returns if its an incorrect guess.
    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    // Returns the secret word.
    public String getSecretWord() {
        return secretWord;
    }

    // Displays when its correctly guessed.
    public String getDisplayedWord() {
        StringBuilder display = new StringBuilder();

        for (char letter : secretWord.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                display.append(letter);
            } else {
                display.append('_');
            }

            display.append(' ');
        }

        return display.toString().trim();
    }

    // Checks if the player guessed the entire word.
    public boolean hasWon() {
        for (char letter : secretWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                return false;
            }
        }

        return true;
    }

    // All the letters are gone.
    public String getGuessedLetters() {
        return guessedLetters.toString();
    }
}