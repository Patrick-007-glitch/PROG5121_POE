
package com.chat;

import java.util.Scanner;

/*
Student: Patrick Tshiluwa Kamunga (ST10497579)
Date: 13 April 2026
This class provides the console menu for:
User registration with validation
Exit program
*/

public class Main {
    
    public static void main(String[] args){
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Quickchat/n");
        
        //I used a Java Syntax reference sheet
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer
            
            if (choice == 1) {
                // Register
                System.out.print("First name: ");
                String firstName = scanner.nextLine();
                
                System.out.print("Last name: ");
                String lastName = scanner.nextLine();
                
                System.out.print("Username (max 5 chars, must contain _ ): ");
                String username = scanner.nextLine();
                
                System.out.print("Password (8+ chars, 1 capital, 1 number, 1 special): ");
                String password = scanner.nextLine();
                
                System.out.print("Phone number (e.g., +27831234567): ");
                String phone = scanner.nextLine();
                
                // Check username
                if (!login.checkUserName(username)) {
                    System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
                }
                // Check password
                else if (!login.checkPasswordComplexity(password)) {
                    System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
                }
                // Check phone
                else if (!login.checkCellPhoneNumber(phone)) {
                    System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                }
                else {
                    System.out.println("Registration successful!");
                }
            }
            else if (choice == 2) {
                System.out.println("Login feature coming in next step!");
                break;
            }
            else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
              
    }
    
    
    
}
