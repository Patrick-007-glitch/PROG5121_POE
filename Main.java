
package com.chat;

import java.util.Scanner;
import org.json.JSONObject;

/*
Student: Patrick Tshiluwa Kamunga (ST10497579)
Date: 13 April 2026
This class provides the console menu for:
User registration with validation
Exit program
*/

public class Main {
    
    // Arrays for Part 3
    public static String[] sentMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] storedMessages = new String[100];
    public static String[] storedHashes = new String[100];
    public static String[] storedIDs = new String[100];
    public static String[] storedRecipients = new String[100];
    public static int sentCount = 0;
    public static int disregardedCount = 0;
    public static int storedCount = 0;
    
    public static void main(String[] args) {
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);
        
        // Load stored messages from JSON file at startup
        loadStoredMessagesFromJSON();
        
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
                
                System.out.print("Phone number (e.g., 0821234567 or +27721234567): ");
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
                        System.out.println("4. Stored Messages");
                        System.out.print("Choose option: ");
                        
                        int quickChoice = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (quickChoice == 1) {
                            System.out.print("How many messages do you want to send? ");
                            int numMessages = scanner.nextInt();
                            scanner.nextLine();
                            
                            // Arrays to store message details
                            String[] recipients = new String[numMessages];
                            String[] messages = new String[numMessages];
                            String[] messageIDs = new String[numMessages];
                            String[] messageHashes = new String[numMessages];
                            
                            int totalSent = 0;
                            
                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Message " + (i + 1) + " ---");
                                
                                // Get recipient
                                System.out.print("Enter recipient phone number (082... or +27...): ");
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
                                    totalSent++;
                                    // Add to sent messages array
                                    sentMessages[sentCount] = messages[i];
                                    sentCount++;
                                } else if (action == 2) {
                                    System.out.println("Press 0 to delete the message.");
                                    // Add to disregarded messages array
                                    disregardedMessages[disregardedCount] = messages[i];
                                    disregardedCount++;
                                } else if (action == 3) {
                                    storeMessageToJSON(messageIDs[i], recipients[i], messages[i], messageHashes[i]);
                                    System.out.println("Message successfully stored.");
                                    // Reload stored messages after saving
                                    loadStoredMessagesFromJSON();
                                } else {
                                    System.out.println("Invalid choice. Message disregarded.");
                                }
                            }
                            System.out.println("\nAll messages received!");
                            System.out.println("Total messages sent: " + totalSent);
                        }
                        else if (quickChoice == 2) {
                            System.out.println("Coming Soon.");
                        }
                        else if (quickChoice == 3) {
                            System.out.println("Logging out...");
                            break; // Exit QuickChat menu
                        }
                        else if (quickChoice == 4) {
                            // Stored Messages Menu
                            while (true) {
                                System.out.println("\n--- Stored Messages Menu ---");
                                System.out.println("1. Display all stored messages (sender & recipient)");
                                System.out.println("2. Display longest stored message");
                                System.out.println("3. Search for message by ID");
                                System.out.println("4. Search for messages by recipient");
                                System.out.println("5. Delete message by hash");
                                System.out.println("6. Display full report");
                                System.out.println("7. Back to main menu");
                                System.out.print("Choose option: ");
                                
                                int storedChoice = scanner.nextInt();
                                scanner.nextLine();
                                
                                if (storedChoice == 1) {
                                    // Display all stored messages
                                    if (storedCount == 0) {
                                        System.out.println("No stored messages found.");
                                    } else {
                                        System.out.println("\n--- All Stored Messages ---");
                                        for (int i = 0; i < storedCount; i++) {
                                            System.out.println("Message " + (i + 1) + ": " + storedMessages[i]);
                                            System.out.println("  Recipient: " + storedRecipients[i]);
                                        }
                                    }
                                }
                                else if (storedChoice == 2) {
                                    // Display longest stored message
                                    if (storedCount == 0) {
                                        System.out.println("No stored messages found.");
                                    } else {
                                        String longest = storedMessages[0];
                                        for (int i = 1; i < storedCount; i++) {
                                            if (storedMessages[i].length() > longest.length()) {
                                                longest = storedMessages[i];
                                            }
                                        }
                                        System.out.println("Longest stored message: " + longest);
                                    }
                                }
                                else if (storedChoice == 3) {
                                    // Search for message by ID
                                    System.out.print("Enter Message ID to search: ");
                                    String searchID = scanner.nextLine();
                                    boolean found = false;
                                    for (int i = 0; i < storedCount; i++) {
                                        if (storedIDs[i].equals(searchID)) {
                                            System.out.println("Found: " + storedMessages[i]);
                                            System.out.println("Recipient: " + storedRecipients[i]);
                                            found = true;
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        System.out.println("Message ID not found.");
                                    }
                                }
                                else if (storedChoice == 4) {
                                    // Search for messages by recipient
                                    System.out.print("Enter recipient phone number to search: ");
                                    String searchRecipient = scanner.nextLine();
                                    boolean found = false;
                                    System.out.println("\n--- Messages for " + searchRecipient + " ---");
                                    for (int i = 0; i < storedCount; i++) {
                                        if (storedRecipients[i].equals(searchRecipient)) {
                                            System.out.println("Message: " + storedMessages[i]);
                                            found = true;
                                        }
                                    }
                                    if (!found) {
                                        System.out.println("No messages found for this recipient.");
                                    }
                                }
                                else if (storedChoice == 5) {
                                    // Delete message by hash
                                    System.out.print("Enter Message Hash to delete: ");
                                    String searchHash = scanner.nextLine();
                                    boolean found = false;
                                    
                                    // Read all messages from JSON and rewrite without the deleted one
                                    try {
                                        java.io.BufferedReader reader = new java.io.BufferedReader(
                                            new java.io.FileReader("stored_messages.json")
                                        );
                                        java.util.ArrayList<String> remainingMessages = new java.util.ArrayList<>();
                                        String line;
                                        while ((line = reader.readLine()) != null) {
                                            JSONObject obj = new JSONObject(line);
                                            if (!obj.getString("messageHash").equals(searchHash)) {
                                                remainingMessages.add(line);
                                            } else {
                                                found = true;
                                            }
                                        }
                                        reader.close();
                                        
                                        if (found) {
                                            // Rewrite the file without the deleted message
                                            java.io.FileWriter writer = new java.io.FileWriter("stored_messages.json");
                                            for (String msg : remainingMessages) {
                                                writer.write(msg + "\n");
                                            }
                                            writer.close();
                                            System.out.println("Message successfully deleted.");
                                            // Reload stored messages
                                            loadStoredMessagesFromJSON();
                                        } else {
                                            System.out.println("Message hash not found.");
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Error deleting message: " + e.getMessage());
                                    }
                                }
                                else if (storedChoice == 6) {
                                    // Display full report
                                    if (storedCount == 0) {
                                        System.out.println("No stored messages found.");
                                    } else {
                                        System.out.println("\n--- Full Stored Messages Report ---");
                                        for (int i = 0; i < storedCount; i++) {
                                            System.out.println("Message " + (i + 1) + ":");
                                            System.out.println("  Message Hash: " + storedHashes[i]);
                                            System.out.println("  Recipient: " + storedRecipients[i]);
                                            System.out.println("  Message: " + storedMessages[i]);
                                            System.out.println();
                                        }
                                    }
                                }
                                else if (storedChoice == 7) {
                                    break; // Back to QuickChat menu
                                }
                                else {
                                    System.out.println("Invalid choice. Try again.");
                                }
                            }
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
    
    // JSON storage method
    public static void storeMessageToJSON(String messageID, String recipient, String message, String messageHash) {
        try {
            JSONObject messageObj = new JSONObject();
            messageObj.put("messageID", messageID);
            messageObj.put("recipient", recipient);
            messageObj.put("message", message);
            messageObj.put("messageHash", messageHash);
            
            java.io.FileWriter file = new java.io.FileWriter("stored_messages.json", true);
            file.write(messageObj.toString() + "\n");
            file.close();
            
            System.out.println("Message saved to JSON file.");
        } catch (Exception e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    
    // Method to load stored messages from JSON file
    public static void loadStoredMessagesFromJSON() {
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.FileReader("stored_messages.json")
            );
            String line;
            storedCount = 0;
            while ((line = reader.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                storedMessages[storedCount] = obj.getString("message");
                storedHashes[storedCount] = obj.getString("messageHash");
                storedIDs[storedCount] = obj.getString("messageID");
                storedRecipients[storedCount] = obj.getString("recipient");
                storedCount++;
            }
            reader.close();
        } catch (Exception e) {
            // File doesn't exist yet - that's fine
            storedCount = 0;
        }
    }
}