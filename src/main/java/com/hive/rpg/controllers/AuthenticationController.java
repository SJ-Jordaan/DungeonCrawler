package com.hive.rpg.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hive.rpg.models.User;


public class AuthenticationController {
    public User user;
    private Scanner scanner;
    public AuthenticationController() {

    }

    public void initialize() {
        User temp = new User();
        temp.setupUser("", "aasdasdasdasdasdsddsadsads");
        user = temp;
        System.out.println("Welcome to The Hero's Journey.");
        do {
            System.out.println("Please enter details to enter game.");
            scanner = new Scanner(System.in);
            Login();
        } while (user.equals(temp));
        scanner.close();
    }

    public void Login() {
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Password:");
        String password = scanner.next();
        User user = new User();
        user.setupUser(username, password);
        if (AuthenticateUser(user)) {
            this.user = user;
            System.out.println("Successfully logged in user: " + this.user.getUsername());
        } else {
            System.out.println("Failed to log in user: " + this.user.getUsername());
            initialize();
        }
    }

    public boolean AuthenticateUser(User user) {
        User[] users = GetUsers();
        if (users.length == 0) {
            return false;
        }
        System.out.println("Authenticating...");
        for (User u : users) {
            if (u.equals(user)) {
                return true;
            }
        }
        return false;
    }

    private User[] GetUsers () {
        try {
            URL url = new URL("https://608287be5dbd2c0017579c41.mockapi.io/api/v1/user");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            final ObjectMapper objectMapper = new ObjectMapper();
            in.close();
            con.disconnect();
            return objectMapper.readValue(content.toString(), User[].class);
        } catch (IOException exception){
            System.out.println("An exception occured: "+ exception.toString());
        }
        return null;
    }


}
