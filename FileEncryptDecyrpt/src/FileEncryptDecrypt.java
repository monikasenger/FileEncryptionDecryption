import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * main GUI class for the File Encryption and Decryption .
 * provides the user interface for selecting files, choosing actions and displaying results.
 */
public class FileEncryptDecrypt extends JFrame {

    static JTextArea textArea;  // text area for file content or encrypted/decrypted result
    static JTextField inputFileField;  // textfield for user input file path
    static JTextField outputFileField;  // textfield for user output file path
    static JComboBox<String> actionComboBox; //dropdown combolist for selecting action:Encrypt or Decrypt
    static JButton processButton;  // button for the encryption/decryption process
    static JButton resetButton;  // button to reset input
    static JLabel statusLabel;  // label to display the status of operations
    FileUIDesign ui = new FileUIDesign();  // File UI Design class instance

    // constructor of file class for set up the UI
    public FileEncryptDecrypt() {
        setupUI();
    }

    /**
     * sets up the UI components and their layout on the JFrame.
     */
    public void setupUI() {
        setTitle("File Encryption/Decryption");
        setSize(800, 500);  // window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit application on window close
        setLocationRelativeTo(null);  // center the window on the screen

        // initialize the components
        textArea = new JTextArea();  // Text area to show the content of the file or processed result
        JScrollPane scrollPane = new JScrollPane(textArea);  // Add scroll instance to the text area

        inputFileField = new JTextField(20);  // text field for the input
        outputFileField = new JTextField(20);  // text field for the output

        // browse buttons  for selecting files
        JButton browseInputButton = ui.createButton("Browse", e -> selectFile(inputFileField));
        JButton browseOutputButton = ui.createButton("Browse", e -> selectFile(outputFileField));

        // dropdown menu for selecting encrypt and decrypt
        actionComboBox = new JComboBox<>(new String[]{"Encrypt", "Decrypt"});

        // process and reset buttons
        processButton = ui.createButton("Process", new ProcessButtonListener());
        resetButton = ui.createButton("Reset", e -> resetFields());

        statusLabel = new JLabel("Status: Ready");  // label show current status

        // create input and status panels using  FileUIDesign class methods
        JPanel inputPanel = ui.createInputPanel(inputFileField, outputFileField, actionComboBox,
                processButton, resetButton, browseInputButton, browseOutputButton);
        JPanel statusPanel = ui.createStatusPanel(statusLabel);

        // add the components to the JFrame using layout managers
        add(scrollPane, BorderLayout.CENTER);  // text area in the center
        add(inputPanel, BorderLayout.NORTH);  // input fields and buttons at the top
        add(statusPanel, BorderLayout.SOUTH);  // status label at the bottom
    }

    /**
     * opens a file chooser dialog for selecting a file.
     * selected file's path is set into the given text field.
     *
     * @param textField The text field where the file path will be displayed
     */
    public void selectFile(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            textField.setText(fileChooser.getSelectedFile().getAbsolutePath());  // set the selected file path to the text field
        }
    }

    /**
     * resets the input fields and status label.
     * clears the input/output file  and resets the status label to "Ready".
     */
    public void resetFields() {
        inputFileField.setText("");  // clear input file path
        outputFileField.setText("");  // clear output file path
        textArea.setText("");  // clear text area
        statusLabel.setText("Status: Ready");  // reset status label to "Ready"
    }

    /**
     * listens the process button click and triggers file for encryption or decryption.
     * displays the process content in the text area and updates the status label.
     */
    public class ProcessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputFilePath = inputFileField.getText();  // get input file path from text field
            String outputFilePath = outputFileField.getText();  // get output file path
            String action = (String) actionComboBox.getSelectedItem();  // get the select action

            // condition check if both input and output file paths are provided
            if (inputFilePath.isEmpty() || outputFilePath.isEmpty()) {
                statusLabel.setText("Status: Please provide both input and output file paths.");
                return;
            }

            statusLabel.setText("Status: Processing...");  // update status to processing
            SwingUtilities.invokeLater(() -> {
                try {
                    String resultContent;
                    if ("Encrypt".equals(action)) {  // condition for action is encrypt
                        resultContent = FileProcessor.encryptFile(inputFilePath, outputFilePath);  // encrypt the file
                        statusLabel.setText("Status: File encrypted successfully.");  // update status to encrypted
                    } else {  // condition for action is decrypt
                        resultContent = FileProcessor.decryptFile(inputFilePath, outputFilePath);  // decrypt the file
                        statusLabel.setText("Status: File decrypted successfully.");  // update status to decrypted
                    }
                    textArea.setText(resultContent);  // display process content in  text area
                } catch (Exception ex) {
                    statusLabel.setText("Status: Error processing file.");  // update status to show error
                    JOptionPane.showMessageDialog(FileEncryptDecrypt.this,
                            "An error occurred: " + ex.getMessage(),  // show error message
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}
