/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vince
 */

package hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanUI extends JFrame {

    private HangmanGame game;

    private final JLabel titleLabel;
    private final JLabel wordLabel;
    private final JLabel guessesLabel;
    private final JLabel statusLabel;
    private final JLabel guessedLettersLabel;

    private final JTextField letterField;
    private final JButton guessButton;
    private final JButton newGameButton;

    private final String[] words = {
        "computer", "programming", "java",
        "keyboard", "software", "hangman", "bug", "variable", "function", "loop", "string", "array", "list", "operator",
        "boolean", "public", "protected", "privated"
    };

    public HangmanUI() {

        setTitle("Hangman Game");
        setSize(500, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Creates the main panel.
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(
                20, 25, 20, 25));

        // The game title.
        titleLabel = new JLabel("HANGMAN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hides the words.
        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        wordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // How many times incorrect.
        guessesLabel = new JLabel();
        guessesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        guessesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Letters the player has guessed.
        guessedLettersLabel = new JLabel();
        guessedLettersLabel.setFont(
                new Font("Arial", Font.PLAIN, 14));
        guessedLettersLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        // Game status updates.
        statusLabel = new JLabel("Guess a letter!");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Letter input field.
        letterField = new JTextField(5);
        letterField.setMaximumSize(new Dimension(120, 30));
        letterField.setHorizontalAlignment(JTextField.CENTER);
        letterField.setFont(new Font("Arial", Font.PLAIN, 16));

        // The buttons.
        guessButton = new JButton("Guess");
        newGameButton = new JButton("New Game");

        // The input from the player.
        JPanel inputPanel = new JPanel();
        inputPanel.add(letterField);
        inputPanel.add(guessButton);

        // Components to main panel.
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(25));

        panel.add(wordLabel);
        panel.add(Box.createVerticalStrut(20));

        panel.add(inputPanel);
        panel.add(Box.createVerticalStrut(15));

        panel.add(guessesLabel);
        panel.add(Box.createVerticalStrut(10));

        panel.add(guessedLettersLabel);
        panel.add(Box.createVerticalStrut(20));

        panel.add(statusLabel);
        panel.add(Box.createVerticalStrut(20));

        panel.add(newGameButton);

        add(panel);

        // Connect buttons to their actions.
        guessButton.addActionListener(e -> makeGuess());
        letterField.addActionListener(e -> makeGuess());
        newGameButton.addActionListener(e -> startNewGame());

        // Start the first ever game.
        startNewGame();
    }

    // Starts a new game with a random word.
    private void startNewGame() {

        int index = (int) (Math.random() * words.length);

        game = new HangmanGame(words[index]);

        letterField.setText("");
        letterField.setEnabled(true);
        guessButton.setEnabled(true);

        statusLabel.setText("Guess a letter!");

        updateDisplay();

        letterField.requestFocusInWindow();
    }

    // Processes the letter entered by player.
    private void makeGuess() {

        String input = letterField.getText().trim();

        // Checks that the player enters one letter.
        if (!input.matches("[a-zA-Z]")) {
            statusLabel.setText(
                    "Please enter one letter from A to Z.");
            return;
        }

        char letter = input.charAt(0);

        // Checks whether the letter was already guessed.
        if (!game.guessLetter(letter)) {
            statusLabel.setText(
                    "You already guessed that letter!");
            letterField.setText("");
            return;
        }

        letterField.setText("");

        // Checks whether the player won or lost.
        if (game.hasWon()) {

            statusLabel.setText("You win! Great job!");

        } else if (game.getIncorrectGuesses() >= 6) {

            statusLabel.setText(
                    "Game over! Word: " + game.getSecretWord());

        } else {

            statusLabel.setText("Guess processed! Keep going.");
        }

        // Refreshes information displayed on screen.
        updateDisplay();

        // Disables guessing after game ends.
        if (game.hasWon() || game.getIncorrectGuesses() >= 6) {
            letterField.setEnabled(false);
            guessButton.setEnabled(false);
        }
    }

    // Updates the word, incorrect guesses, and guessed letters.
    private void updateDisplay() {

        wordLabel.setText(game.getDisplayedWord());

        guessesLabel.setText(
                "Incorrect guesses: "
                + game.getIncorrectGuesses()
                + " / 6"
        );

        guessedLettersLabel.setText(
                "Guessed letters: "
                + game.getGuessedLetters()
        );
    }

    // Main method that launches the application.
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            HangmanUI window = new HangmanUI();
            window.setVisible(true);
        });
    }

}
