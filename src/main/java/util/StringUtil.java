package util;

import model.Block;

import java.security.MessageDigest;

public class StringUtil {

    // takes a string and applies the SHA256 algorithm to it - returns the generated signature as a string
    public static String applySha256(String input) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Applies SHA256 to our input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // contains 'hash' as hexadecimal
                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
        }

        catch (Exception e) {
            throw new RuntimeException();
        }
    }

}