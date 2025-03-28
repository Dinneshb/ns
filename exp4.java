import java.util.Scanner;

class Hellman {
    // Fast modular exponentiation: (a^b) % p
    private static long power(long a, long b, long p) {
        long res = 1;
        a = a % p;
        while (b > 0) {
            if ((b & 1) == 1) { // If b is odd, multiply by a
                res = (res * a) % p;
            }
            b = b >> 1; // Divide b by 2
            a = (a * a) % p; // Square a
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner instance

        System.out.print("Enter a prime number P: ");
        long P = scanner.nextLong();

        System.out.print("Enter a primitive root G: ");
        long G = scanner.nextLong();

        // User input for private keys a and b
        System.out.print("Enter Alice's private key (a): ");
        long a = scanner.nextLong();

        System.out.print("Enter Bob's private key (b): ");
        long b = scanner.nextLong();

        long x = power(G, a, P); // Alice's public key
        long y = power(G, b, P); // Bob's public key

        long ka = power(y, a, P); // Secret key for Alice
        long kb = power(x, b, P); // Secret key for Bob

        System.out.println("Public key for Alice (x): " + x);
        System.out.println("Public key for Bob (y): " + y);
        System.out.println("Secret key for Alice: " + ka);
        System.out.println("Secret key for Bob: " + kb);

        if (ka == kb) {
            System.out.println("Key exchange successful! Shared secret key: " + ka);
        } else {
            System.out.println("Error in key exchange!");
        }

        scanner.close(); // Close the scanner
    }
}
