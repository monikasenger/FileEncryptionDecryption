import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * class to handle file operations like reading, writing, encryption, and decryption.
 */
public class FileProcessor {

    /**
     * encrypts the content of a file and saves the result to output file.
     *
     * @param inputFilePath  The input file path to be encrypted.
     * @param outputFilePath The output file path where encrypted data will be saved.
     * @return The encrypted content of the file.
     * @throws IOException If file reading/writing fails.
     */
    public static String encryptFile(String inputFilePath, String outputFilePath) throws IOException {
        String content = readFile(inputFilePath);  // read content of file
        String encryptedContent = encrypt(content, 3);  // encrypt the content
        saveToFile(outputFilePath, encryptedContent);  // save encrypted content to output file
        return encryptedContent;
    }

    /**
     * decrypts the content of a file and saves the result tooutput file.
     *
     * @param inputFilePath  The input file path to be decrypted.
     * @param outputFilePath The output file path where decrypted data will be saved.
     * @return The decrypted content of the file.
     * @throws IOException If file reading/writing fails.
     */
    public static String decryptFile(String inputFilePath, String outputFilePath) throws IOException {
        String content = readFile(inputFilePath);  // read content of file
        String decryptedContent = encrypt(content, -3);  // decrypt the content
        saveToFile(outputFilePath, decryptedContent);  // save decrypted content tooutput file
        return decryptedContent;
    }

    /**
     * reads content of a file and returns it as a string.
     *
     * @param filePath  path to the file to be read.
     * @return  content of the file.
     * @throws IOException If file reading fails.
     */
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));  // read all bytes from file and convert to string
    }

    /**
     * saves given content to a file at specified path.
     *
     * @param filePath path to the file where the content will be saved.
     * @param content  content to be saved in the file.
     * @throws IOException If file writing fails.
     */
    public static void saveToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());  // write content to file
    }

    /**
     *performs encryption or decryption based on the shift value.
     *
     * @param text   text to be encrypted or decrypted.
     * @param shift shift value for encryption (positive) or decryption (negative).
     * @return  processed text after encryption or decryption.
     */
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {  // process only alphabetic characters
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                result.append((char) ((ch - base + shift + 26) % 26 + base));
            } else {
                result.append(ch);  // non-alphabetic characters are not changed
            }
        }
        return result.toString();  // return encrypted or decrypted text
    }
}
