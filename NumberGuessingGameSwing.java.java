import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameSwing extends JFrame {
    private int targetNumber;
    private int maxAttempts = 10;
    private int attempts = 0;
    private int score = 0;

    private JLabel feedbackLabel;
    private JTextField guessField;

    public NumberGuessingGameSwing() {
        setTitle("Number Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        feedbackLabel = new JLabel("Enter your guess:");
        add(feedbackLabel, BorderLayout.NORTH);

        guessField = new JTextField(10);
        guessField.addActionListener(new GuessListener());
        add(guessField, BorderLayout.CENTER);

        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameListener());
        add(newGameButton, BorderLayout.SOUTH);

        newGame();
    }

    private void newGame() {
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("Enter your guess:");
        guessField.setText("");
    }

    private class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (attempts < maxAttempts) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());

                    if (userGuess == targetNumber) {
                        feedbackLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                        score += 1;
                    } else if (userGuess < targetNumber) {
                        feedbackLabel.setText("Too low. Try again.");
                    } else {
                        feedbackLabel.setText("Too high. Try again.");
                    }

                    attempts++;

                    if (attempts == maxAttempts) {
                        feedbackLabel.setText("Sorry, you've reached the maximum number of attempts. The correct number was " + targetNumber + ".");
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Invalid input. Please enter a number.");
                }

                guessField.setText("");
            }
        }
    }

    private class NewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newGame();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameSwing game = new NumberGuessingGameSwing();
            game.setVisible(true);
        });
    }
}
