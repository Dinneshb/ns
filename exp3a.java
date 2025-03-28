import java.io.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class exp3a {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter text to encrypt: ");
        String plaintext = new java.util.Scanner(System.in).nextLine();

        // Generate a DES key
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();

        // Save the key to a file
        try (ObjectOutputStream keyOut = new ObjectOutputStream(new FileOutputStream("desKey.ser"))) {
            keyOut.writeObject(key);
        }

        // Encrypt the text
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        String encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));

        System.out.println("Encrypted Text: " + encryptedText);
    }
}
