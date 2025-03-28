import java.util.*;

class Playfair {
    String key, cipherText;
    char[][] matrix = new char[5][5];

    Playfair(String key, String cipherText) {
        this.key = key.toLowerCase().replaceAll("j", "i");
        this.cipherText = cipherText.toLowerCase().replaceAll("j", "i");
    }

    void generateCipherKey() {
        String newKey = key + "abcdefghiklmnopqrstuvwxyz"; // 'j' is omitted
        Set<Character> seen = new LinkedHashSet<>();
        
        for (char c : newKey.toCharArray()) {
            if (c >= 'a' && c <= 'z' && c != 'j') {
                seen.add(c);
            }
        }

        Iterator<Character> it = seen.iterator();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = it.next();
            }
        }
    }

    int[] findPos(char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    String decryptMessage() {
        StringBuilder plainText = new StringBuilder();
        
        for (int i = 0; i < cipherText.length(); i += 2) {
            int[] p1 = findPos(cipherText.charAt(i));
            int[] p2 = findPos(cipherText.charAt(i + 1));

            if (p1[0] == p2[0]) {  // Same row, move left
                plainText.append(matrix[p1[0]][(p1[1] + 4) % 5])
                         .append(matrix[p2[0]][(p2[1] + 4) % 5]);
            } else if (p1[1] == p2[1]) { // Same column, move up
                plainText.append(matrix[(p1[0] + 4) % 5][p1[1]])
                         .append(matrix[(p2[0] + 4) % 5][p2[1]]);
            } else { // Rectangle swap
                plainText.append(matrix[p1[0]][p2[1]])
                         .append(matrix[p2[0]][p1[1]]);
            }
        }
        return plainText.toString();
    }
}

public class exp1d {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Key:");
        String key = sc.nextLine();
        
        System.out.println("Enter Cipher Text:");
        String cipherText = sc.nextLine();
        
        Playfair cipher = new Playfair(key, cipherText);
        cipher.generateCipherKey();
        
        System.out.println("Key Matrix:");
        for (char[] row : cipher.matrix) {
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println("Decrypted Text: " + cipher.decryptMessage());
        sc.close();
    }
}
