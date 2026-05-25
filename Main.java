
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
    
    public static void main(String[] args) {
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Quickchat\n");
        
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
                
                if (!login.checkUserName(username)) {
                    System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
                }
                else if (!login.checkPasswordComplexity(password)) {
                    System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
                }
                else if (!login.checkCellPhoneNumber(phone)) {
                    System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                }
                else {
                    String result = login.registerUser(username, password, firstName, lastName, phone);
                    System.out.println(result);
                }
            }
            else if (choice == 2) {
                // LOGIN
                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();
                
                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();
                
                String status = login.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(status);
                
                if (status.startsWith("Welcome")) {
                    // LOGIN SUCCESSFUL - Now show QuickChat menu
                    System.out.println("\n--- QuickChat Menu ---");
                    System.out.println("1. Send Message");
                    System.out.println("2. Show Recently Sent Messages (Coming Soon)");
                    System.out.println("3. Logout");
                    System.out.print("Choose option: ");
                    
                    int quickChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (quickChoice == 1) {
                        System.out.println("Send Message feature - coming next!");
                    } else if (quickChoice == 2) {
                        System.out.println("Coming Soon.");
                    } else if (quickChoice == 3) {
                        System.out.println("Logging out...");
                    }
                }
            }
            else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}