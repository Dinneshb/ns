import java.util.*;

public class exp2e {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for message
        System.out.print("Enter the message to encrypt: ");
        String msg = scanner.nextLine().replaceAll("\\s", ""); // Remove spaces

        // Get user input for key
        System.out.print("Enter the key (comma-separated numbers): ");
        String[] keyInput = scanner.nextLine().split(",");
        int[] key = Arrays.stream(keyInput).mapToInt(Integer::parseInt).toArray();

        // Validate key (must have unique numbers)
        if (!isValidKey(key)) {
            System.out.println("Error: The key must contain unique numbers!");
            return;
        }

        String cipher = encryptMessage(msg, key);
        System.out.println("Encrypted Message: " + cipher);

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

    static String encryptMessage(String msg, int[] key) {
        int col = key.length;
        int row = (int) Math.ceil((double) msg.length() / col);
        char[][] matrix = new char[row][col];

        // Fill the matrix row-wise
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (k < msg.length()) {
                    matrix[i][j] = msg.charAt(k++);
                } else {
                    matrix[i][j] = '_'; // Padding character
                }
            }
        }

        // Read the columns in key order
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < col; i++) {
            int colIndex = key[i] - 1; // Convert 1-based key to 0-based index
            if (colIndex >= col || colIndex < 0) {
                System.out.println("Invalid key index: " + key[i]);
                return "";
            }
            for (int j = 0; j < row; j++) {
                cipher.append(matrix[j][colIndex]);
            }
        }
        return cipher.toString();
    }
}
