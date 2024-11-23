import javax.swing.*;

/**
 * class to enter the File Encryption/Decryption application.
 * launches the GUI.
 */
public class FileEncryptDecrypt_Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileEncryptDecrypt().setVisible(true));//start the application
    }
}


