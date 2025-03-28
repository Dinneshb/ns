import java.util.*;

public class exp2d {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for cipher text
        System.out.print("Enter the encrypted message: ");
        String cipher = scanner.nextLine();

        // Get user input for key
        System.out.print("Enter the key (word): ");
        String key = scanner.nextLine();

        String message = decryptMessage(cipher, key);
        System.out.println("Decrypted Message: " + message);

        scanner.close();
    }

    static String decryptMessage(String cipher, String key) {
        int col = key.length();
        int row = (int) Math.ceil((double) cipher.length() / col);
        char[][] matrix = new char[row][col];

        // Generate column order based on sorted key
        Integer[] colOrder = getColumnOrder(key);

        // Fill the matrix column-wise using key order
        int k = 0;
        for (int i = 0; i < col; i++) {
            int colIndex = colOrder[i];
            for (int j = 0; j < row; j++) {
                if (k < cipher.length()) {
                    matrix[j][colIndex] = cipher.charAt(k++);
                } else {
                    matrix[j][colIndex] = ' '; // Padding with space
                }
            }
        }

        // Read the message row-wise
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != ' ') { // Ignore padding
                    msg.append(matrix[i][j]);
                }
            }
        }
        return msg.toString();
    }

    static Integer[] getColumnOrder(String key) {
        int len = key.length();
        Character[] keyChars = new Character[len];
        Integer[] colOrder = new Integer[len];

        for (int i = 0; i < len; i++) {
            keyChars[i] = key.charAt(i);
        }

        // Create a list of indexes and sort based on the characters
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            indices.add(i);
        }

        indices.sort(Comparator.comparingInt(index -> keyChars[index]));

        // Store the sorted order
        for (int i = 0; i < len; i++) {
            colOrder[i] = indices.get(i);
        }

        return colOrder;
    }
}

