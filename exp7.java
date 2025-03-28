import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class exp7 {
    // Signing Algorithm
    private static final String SIGNING_ALGORITHM = "SHA256withRSA";
    private static final String RSA = "RSA";

    // Generate RSA KeyPair (Public & Private Key)
    public static KeyPair generateRSAKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    // Create Digital Signature
    public static byte[] createDigitalSignature(byte[] input, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(input);
        return signature.sign();
    }

    // Verify Digital Signature
    public static boolean verifyDigitalSignature(byte[] input, byte[] signatureToVerify, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(input);
        return signature.verify(signatureToVerify);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to sign: ");
        String input = scanner.nextLine();
        scanner.close(); // Close scanner after input

        // Generate RSA KeyPair
        KeyPair keyPair = generateRSAKeyPair();

        // Create Digital Signature
        byte[] signature = createDigitalSignature(input.getBytes(), keyPair.getPrivate());
        String encodedSignature = Base64.getEncoder().encodeToString(signature);

        // Print Digital Signature
        System.out.println("\nDigital Signature (Base64 Encoded):");
        System.out.println(encodedSignature);

        // Verify Digital Signature
        boolean isVerified = verifyDigitalSignature(input.getBytes(), Base64.getDecoder().decode(encodedSignature), keyPair.getPublic());
        System.out.println("\nSignature Verified: " + isVerified);
    }
}
