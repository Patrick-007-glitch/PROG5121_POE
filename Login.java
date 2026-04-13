
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
        return phonenumber.startsWith("+27") && phonenumber.length() <=13;
    }
}
