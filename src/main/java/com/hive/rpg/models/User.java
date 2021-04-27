package com.hive.rpg.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    public String id;
    public String createdAt;
    public String username;
    public String passwordHash;

    public void setupUser(String username, String password) {
        this.username = username;
        if (password.equals("")) {
            passwordHash = "";
        } else {
            passwordHash = hash(password);
        }
    }

    public boolean equals (User compare) {
        return (username.equals(compare.username) && passwordHash.equals(compare.passwordHash));
    }

    @Override
    public String toString() {
        return "{\"login\": \""+username+"\", \"password\": \"" +passwordHash+"\"}";
    }

    public String getUsername() {
        return username;
    }

    private String hash(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException exception) {
            System.out.println(exception.toString());
        }
        return "";
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
