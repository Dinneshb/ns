import java.util.Arrays;
import java.util.Scanner;
public class exp2b {
 public static String decryptRailFence(String cipher, int key) {
 char[][] rail = new char[key][cipher.length()];
 for (int i = 0; i < key; i++) {
 Arrays.fill(rail[i], '\n');
 } boolean dirDown = true;
 int row = 0, col = 0;
 for (int i = 0; i < cipher.length(); i++) {
 if (row == 0) dirDown = true;
 if (row == key - 1) dirDown = false;
 rail[row][col++] = '*';
 if (dirDown) row++;
 else row--; } int index = 0;
 for (int i = 0; i < key; i++) {
 for (int j = 0; j < cipher.length(); j++) {
 if (rail[i][j] == '*' && index < cipher.length()) {
 rail[i][j] = cipher.charAt(index++);
 } } }
 StringBuilder result = new StringBuilder();
 row = 0;
 col = 0;
 for (int i = 0; i < cipher.length(); i++) {
 if (row == 0) dirDown = true;
 if (row == key - 1) dirDown = false;
 if (rail[row][col] != '*') result.append(rail[row][col++]);
 if (dirDown) row++;
 else row--;
 } return result.toString(); }
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 System.out.println("enter cipher text" );
 
 String cipherText = sc.nextLine();
 System.out.println("enter key" );
 int key = sc.nextInt();
 String decryptedMessage = decryptRailFence(cipherText, key);
 System.out.println("Decrypted Message: " + decryptedMessage);
 sc.close(); }}
