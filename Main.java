
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
                    while (true) {
                        System.out.println("\n--- QuickChat Menu ---");
                        System.out.println("1. Send Message");
                        System.out.println("2. Show Recently Sent Messages (Coming Soon)");
                        System.out.println("3. Logout");
                        System.out.print("Choose option: ");
                        
                        int quickChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (quickChoice == 1) {
                            if (quickChoice == 1) {
                            System.out.print("How many messages do you want to send? ");
                        int numMessages = scanner.nextInt();
                        scanner.nextLine();
    
                        // Arrays to store message details
                        String[] recipients = new String[numMessages];
                        String[] messages = new String[numMessages];
                        String[] messageIDs = new String[numMessages];
                        String[] messageHashes = new String[numMessages];
    
                        int totalSent = 0;  // <--- LINE 1: ADD THIS HERE (before the loop)
    
                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + " ---");
        
                        // Get recipient
                        System.out.print("Enter recipient phone number (+27...): ");
                        recipients[i] = scanner.nextLine();
        
                        // Get message text
                        System.out.print("Enter your message (max 250 chars): ");
                        messages[i] = scanner.nextLine();
        
                        // Check message length
                    if (messages[i].length() > 250) {
                        System.out.println("Please enter a message of less than 250 characters.");
                        i--; // repeat this message
                        continue;
                        }
       
                        // Generate Message ID (10 random digits)
                        messageIDs[i] = "";
                        for (int j = 0; j < 10; j++) {
                        int digit = (int)(Math.random() * 10);
                        messageIDs[i] += digit;
                        }
        
                        // Generate Message Hash
                        String firstWord = messages[i].split(" ")[0];
                        String lastWord = messages[i].split(" ")[messages[i].split(" ").length - 1];
                        messageHashes[i] = messageIDs[i].substring(0, 2) + ":" + (i + 1) + ":" + firstWord + lastWord;
                        messageHashes[i] = messageHashes[i].toUpperCase();
        
                        // Display the message details
                        System.out.println("\n--- Message Details ---");
                        System.out.println("Message ID: " + messageIDs[i]);
                        System.out.println("Message Hash: " + messageHashes[i]);
                        System.out.println("Recipient: " + recipients[i]);
                        System.out.println("Message: " + messages[i]);
        
                        // Ask user what to do with the message
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message (save to JSON)");
                        System.out.print("Choose option: ");
        
                        int action = scanner.nextInt();
                        scanner.nextLine();
        
                        if (action == 1) {
                        System.out.println("Message successfully sent.");
                        totalSent++;  // <--- LINE 2: ADD THIS HERE (inside the send option)
                        } else if (action == 2) {
                        System.out.println("Press 0 to delete the message.");
                        } else if (action == 3) {
                        System.out.println("Message successfully stored.");
                        } else {
                        System.out.println("Invalid choice. Message disregarded.");
                        }
                        }
                        System.out.println("\nAll messages received!");
                        System.out.println("Total messages sent: " + totalSent);  // <--- LINE 3: ADD THIS HERE
                        }
                        System.out.println("\nAll messages received!");
                        }
                        else if (quickChoice == 2) {
                            System.out.println("Coming Soon.");
                        }
                        else if (quickChoice == 3) {
                            System.out.println("Logging out...");
                            break; // Exit QuickChat menu
                        }
                        else {
                            System.out.println("Invalid choice. Try again.");
                        }
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
