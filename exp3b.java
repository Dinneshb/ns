import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
public class exp3b {
 public static void main(String[] args) throws Exception {
 Scanner scanner = new Scanner(System.in);
 SecretKey secretKey;
 try (ObjectInputStream keyIn = new ObjectInputStream(new
FileInputStream("desKey.ser"))) { secretKey = (SecretKey) keyIn.readObject();
 } System.out.print("Enter encrypted text: ");
 String encryptedText = scanner.nextLine();
 Cipher cipher = Cipher.getInstance("DES");
 cipher.init(Cipher.DECRYPT_MODE, secretKey);
 byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
 String decryptedText = new String(decryptedBytes);
 System.out.println("Decrypted Text: " + decryptedText);
 scanner.close(); }}