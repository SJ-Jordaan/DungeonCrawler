package com.hive.rpg.controllers;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class AuthenticationController {

    private static String APPID = "8694F5CE-D700-488E-95F9-57336B8CA0DC";
    private static String APIKEY = "30E61AB3-1679-45F8-B194-2848F93DBF76";

    public static boolean auth = true;
    public BackendlessUser user;
    private Scanner scanner;
    private Console console = System.console();

    public AuthenticationController() {

    }

    public String initialize() {
        Backendless.initApp(this, APPID, APIKEY);
        user = new BackendlessUser();
        System.out.println("Welcome to The Hero's Journey.");
        do {
            System.out.println("[L]ogin");
            System.out.println("[R]egister");
            scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            if (choice.toLowerCase().equals("l")) {
                login();
            } else if (choice.toLowerCase().equals("r")) {
                register();
            } else {

            }
        } while (auth);
        scanner.close();
        return user.getProperty("name").toString();
    }

    public void register() {
        System.out.println("Username:");
        user.setProperty("name", scanner.nextLine());
        user.setPassword(hash(new String(console.readPassword("Password: "))));
        try {
            Backendless.UserService.register(user);
            System.out.println("Successfully registered");
        } catch (Exception e) {
            System.out.println("Registration failed");
        }
    }

    public void login() {
        System.out.println("Username:");
        String username = scanner.nextLine();
        String password = hash(new String(console.readPassword("Password: ")));

        try {
            Backendless.UserService.login(username, password);
            System.out.println("Login successful");
            auth = false;
        } catch (Exception e) {
            System.out.println("Login Failed");
        }
    }

    private String hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
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
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
