import java.util.*;

public class exp2f {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for cipher text
        System.out.print("Enter the encrypted message: ");
        String cipher = scanner.nextLine();

        // Get user input for key
        System.out.print("Enter the key (comma-separated numbers): ");
        String[] keyInput = scanner.nextLine().split(",");
        int[] key = Arrays.stream(keyInput).mapToInt(Integer::parseInt).toArray();

        // Validate key
        if (!isValidKey(key)) {
            System.out.println("Error: The key must contain unique numbers!");
            return;
        }

        String message = decryptMessage(cipher, key);
        System.out.println("Decrypted Message: " + message);

        scanner.close();
    }

    static boolean isValidKey(int[] key) {
        Set<Integer> set = new HashSet<>();
        for (int num : key) {
            if (!set.add(num)) {
                return false; // Duplicate key found
            }
        }
        return true;
    }

    static String decryptMessage(String cipher, int[] key) {
        int col = key.length;
        int row = (int) Math.ceil((double) cipher.length() / col);
        char[][] matrix = new char[row][col];

        // Fill the matrix with spaces (default padding)
        for (char[] chars : matrix) {
            Arrays.fill(chars, ' ');
        }

        // Fill the matrix column-wise using key order
        int k = 0;
        for (int i = 0; i < col; i++) {
            int colIndex = key[i] - 1; // Convert 1-based key to 0-based index
            if (colIndex >= col || colIndex < 0) {
                System.out.println("Invalid key index: " + key[i]);
                return "";
            }
            for (int j = 0; j < row; j++) {
                if (k < cipher.length()) {
                    matrix[j][colIndex] = cipher.charAt(k++);
                }
            }
        }

        // Read the message row-wise
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != ' ' && matrix[i][j] != '_') { // Ignore padding
                    msg.append(matrix[i][j]);
                }
            }
        }
        return msg.toString();
    }
}
