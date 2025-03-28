import java.util.*;
public class exp2c {
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);
 // Get user input for message
 System.out.print("Enter the message to encrypt: ");
 String msg = scanner.nextLine().replaceAll("\\s", ""); // Remove spaces
 // Get user input for key
 System.out.print("Enter the key (word): ");
 String key = scanner.nextLine();
 String cipher = encryptMessage(msg, key);
 System.out.println("Encrypted Message: " + cipher);
 scanner.close();
 } static String encryptMessage(String msg, String key) {
 int col = key.length();
 int row = (int) Math.ceil((double) msg.length() / col);
 char[][] matrix = new char[row][col];
 // Fill the matrix row-wise
 int k = 0;
 for (int i = 0; i < row; i++) {for (int j = 0; j < col; j++) {
    if (k < msg.length()) {
    matrix[i][j] = msg.charAt(k);
    } else {
    matrix[i][j] = '_'; // Padding character
    } k++;
    } }
    // Generate column order based on sorted key
    Integer[] colOrder = getColumnOrder(key);
    // Read the columns in sorted key order
    StringBuilder cipher = new StringBuilder();
    for (int i = 0; i < col; i++) {
    int colIndex = colOrder[i];
    for (int j = 0; j < row; j++) {
    cipher.append(matrix[j][colIndex]); } }
    return cipher.toString(); }
    static Integer[] getColumnOrder(String key) {
    Character[] keyChars = new Character[key.length()];
    for (int i = 0; i < key.length(); i++) {
    keyChars[i] = key.charAt(i); }
    Arrays.sort(keyChars); // Sort key alphabetically
    Integer[] colOrder = new Integer[key.length()];
    for (int i = 0; i < key.length(); i++) {
    colOrder[i] = key.indexOf(keyChars[i]); }
    return colOrder; }}