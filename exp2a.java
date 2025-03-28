import java.util.*;

public class exp2a {
    public static String encryptRailFence(String text, int key) {
        if (key <= 1) return text; // No encryption needed for key 1
        
        char[][] rail = new char[key][text.length()];
        
        // Fill matrix with spaces
        for (char[] row : rail) {
            Arrays.fill(row, ' ');
        }

        boolean dirDown = false;
        int row = 0, col = 0;

        // Place characters in the rail matrix
        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1) {
                dirDown = !dirDown; // Change direction at the top and bottom
            }
            rail[row][col++] = text.charAt(i);
            row += (dirDown) ? 1 : -1; // Move up or down
        }

        // Read encrypted message row-wise
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] != ' ') {
                    result.append(rail[i][j]);
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the text:");
        String text = sc.nextLine();
        
        System.out.println("Enter the key:");
        int key = sc.nextInt();
        
        String encryptedMessage = encryptRailFence(text, key);
        System.out.println("Encrypted Message: " + encryptedMessage);
        
        sc.close();
    }
}
