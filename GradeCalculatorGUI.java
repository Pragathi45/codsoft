import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grade Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLayout(new FlowLayout());

            // Create input fields for marks and a button to calculate grades
            JLabel label = new JLabel("Enter marks obtained in each subject (out of 100): ");
            frame.add(label);

            JTextField marksField = new JTextField(20);
            frame.add(marksField);

            JButton calculateButton = new JButton("Calculate");
            frame.add(calculateButton);

            // Create a text area to display the results
            JTextArea resultArea = new JTextArea(10, 20);
            resultArea.setEditable(false); // Make it read-only
            frame.add(resultArea);

            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String marksText = marksField.getText();
                    String[] marksArray = marksText.split("\\s+");
                    int totalMarks = 0;
                    int numSubjects = marksArray.length;

                    for (String mark : marksArray) {
                        try {
                            int markValue = Integer.parseInt(mark);
                            if (markValue < 0 || markValue > 100) {
                                resultArea.setText("Invalid mark input. Marks should be between 0 and 100.");
                                return;
                            }
                            totalMarks += markValue;
                        } catch (NumberFormatException ex) {
                            resultArea.setText("Invalid mark input. Please enter valid numbers.");
                            return;
                        }
                    }

                    double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;

                    String grade;
                    if (averagePercentage >= 90) {
                        grade = "A+";
                    } else if (averagePercentage >= 80) {
                        grade = "A";
                    } else if (averagePercentage >= 70) {
                        grade = "B";
                    } else if (averagePercentage >= 60) {
                        grade = "C";
                    } else if (averagePercentage >= 50) {
                        grade = "D";
                    } else {
                        grade = "F";
                    }

                    String result = "Total Marks: " + totalMarks + "\nAverage Percentage: " + averagePercentage + "%\nGrade: " + grade;
                    resultArea.setText(result);
                }
            });

            frame.setVisible(true);
        });
    }
}
