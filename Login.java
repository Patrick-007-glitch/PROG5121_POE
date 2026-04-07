
package com.chat;

public class Login {
    
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <=5;
    }
    
    public boolean checkpasswordComplexity(String password){
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
        return phonenumber.startsWith("+27") && phonenumber.length() <=10;
    }
}
