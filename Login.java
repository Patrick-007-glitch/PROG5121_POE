
package com.chat;

/*
Student: Patrick Tshiluwa Kamunga (ST10497579)
Date: 13 April 2026
 
This class handles user validation for:
Username (must contain _ and be ≤ 5 chars)as stated in the POE 
Password (8+ chars, 1 capital, 1 number, 1 special)
Phone number (must start with +27)
*/

public class Login {
    
    // Storage for registered user
    private String storedUsername;
    private String storedPassword;
    private String storedFirstName;
    private String storedLastName;
    private String storedPhoneNumber;
    private boolean isRegistered = false;
    
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <=5;
    }
    /*
    I actually wanted to go with string at first it wasn't working but in the 
    class gourp some one advised me to use a boolean instead
    */

    public boolean checkPasswordComplexity(String password){ //Use Capital P
        if (password == null || password.length()< 8 ) return false;
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial =false;
        
        for (char c : password.toCharArray()){
            if (Character.isUpperCase(c)) hasCapital= true;
            if (Character.isDigit(c)) hasNumber=true;
            if (!Character.isLetterOrDigit(c)) hasSpecial=true;
        }
        return hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber(String phonenumber){
            if (phonenumber == null) return false;
               
        if (phonenumber.startsWith("+27")) {
        return phonenumber.length() <= 13 && phonenumber.length() >= 12;
        }
             
        return phonenumber.length() == 10 && phonenumber.matches("0\\d{9}");
        }
        public String registerUser(String username, String password, String firstName, 
                               String lastName, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username " +
                   "contains an underscore and is no more than five characters in length.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password " +
                   "contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedFirstName = firstName;
        this.storedLastName = lastName;
        this.storedPhoneNumber = phoneNumber;
        this.isRegistered = true;
        
        return "User registered successfully.";
    }
    public boolean loginUser(String username, String password) {
        if (!isRegistered) return false;
        return storedUsername.equals(username) && storedPassword.equals(password);
    }
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + storedFirstName + ", " + storedLastName + 
                   " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
