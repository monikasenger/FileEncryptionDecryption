import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * class for creating reusable UI components and layouts for  application.
 */
public class FileUIDesign {

    /**
     * creates a reusable button with text and action listener.
     *
     * @param text     The button text.
     * @param listener The action listener for the button.
     * @return The configured JButton.
     */
    public static JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);  // add listener to button
        return button;  //return the button object
    }

    /**
     *creates and returns the input panel,which consists of file input fields,action combo box,and process/reset buttons.
     *
     * @param inputFileField      The text field for input file path.
     * @param outputFileField     The text field for output file path.
     * @param actionComboBox      The combo box for choosing Encrypt or Decrypt.
     * @param processButton       The button to initiate the file process.
     * @param resetButton         The button to reset the fields.
     * @param browseInputButton   Button to browse and select an input file.
     * @param browseOutputButton  Button to browse and select an output file.
     * @return The constructed JPanel containing all input components.
     */
    public static JPanel createInputPanel(JTextField inputFileField, JTextField outputFileField,
                                          JComboBox<String> actionComboBox, JButton processButton,
                                          JButton resetButton, JButton browseInputButton,
                                          JButton browseOutputButton) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));//set layout with 3 rows

        // first row- input file field with browse button
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.add(new JLabel("Input File:"));
        row1.add(inputFileField);
        row1.add(browseInputButton);

        // second row- output file field with browse button
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.add(new JLabel("Output File:"));
        row2.add(outputFileField);
        row2.add(browseOutputButton);

        // third row- action on combo box and process/reset buttons
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row3.add(new JLabel("Action:"));
        row3.add(actionComboBox);
        row3.add(processButton);
        row3.add(resetButton);

        // add rows to the panel
        panel.add(row1);
        panel.add(row2);
        panel.add(row3);

        return panel;  //return the constructed panel
    }

    /**
     * creates status panel for displaying the current status
     *
     * @param statusLabel The label to display the status message.
     * @return The constructed JPanel containing the status label.
     */
    public static JPanel createStatusPanel(JLabel statusLabel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(statusLabel, BorderLayout.WEST);  //status label add to left side of the panel
        return panel;
    }
}
