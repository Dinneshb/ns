import java.util.*;
class Playfair {
String key, plainText;
char[][] matrix = new char[5][5];
Playfair(String key, String plainText) {
this.key = key.toLowerCase().replaceAll("[j]", "i");
this.plainText = plainText.toLowerCase().replaceAll("[j]", "i");
} void generateCipherKey() {
String newKey = (key + "abcdefghiklmnopqrstuvwxyz")
.chars()
.distinct()
.filter(c -> c != 'j')
.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
.toString();
for (int i = 0, k = 0; i < 5; i++)
for (int j = 0; j < 5; j++)
matrix[i][j] = newKey.charAt(k++);
} String formatPlainText() {
StringBuilder sb = new StringBuilder(plainText);
for (int i = 0; i < sb.length(); i += 2)
if (i + 1 < sb.length() && sb.charAt(i) == sb.charAt(i + 1))
sb.insert(i + 1, 'x');
if (sb.length() % 2 == 1) sb.append('x');
return sb.toString();
}
int[] findPos(char ch) {
for (int i = 0; i < 5; i++)
for (int j = 0; j < 5; j++)
if (matrix[i][j] == ch) return new int[]{i, j};
return null;
} String encryptMessage() {
StringBuilder cipherText = new StringBuilder();
String message = formatPlainText();
for (int i = 0; i < message.length(); i += 2) {
int[] p1 = findPos(message.charAt(i));
int[] p2 = findPos(message.charAt(i + 1));
if (p1[0] == p2[0]) {
cipherText.append(matrix[p1[0]][(p1[1] + 1) % 5])
.append(matrix[p2[0]][(p2[1] + 1) % 5]);
} else if (p1[1] == p2[1]) {
cipherText.append(matrix[(p1[0] + 1) % 5][p1[1]])
.append(matrix[(p2[0] + 1) % 5][p2[1]]);
} else {
cipherText.append(matrix[p1[0]][p2[1]])
.append(matrix[p2[0]][p1[1]]);
} } return cipherText.toString();
} } public class exp1c {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
String key = sc.nextLine();
String plainText = sc.nextLine();
Playfair cipher = new Playfair(key, plainText);
cipher.generateCipherKey();
System.out.println("Key Matrix:");
for (char[] row : cipher.matrix)
System.out.println(Arrays.toString(row));
System.out.println("Cipher Text: " + cipher.encryptMessage()); }}