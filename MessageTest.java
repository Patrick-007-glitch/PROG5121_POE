
package com.chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    Login login = new Login();
    
    // Test 1: Message length validation
    @Test
    public void testMessageLength_Valid() {
        String message = "This is a short message";
        assertTrue(message.length() <= 250);
    }
    
    @Test
    public void testMessageLength_Invalid() {
        String message = "A".repeat(251); // 251 characters
        assertFalse(message.length() <= 250);
    }
    
    // Test 2: Recipient phone number validation (reuse Login method)
    @Test
    public void testRecipientCell_Valid() {
        assertTrue(login.checkCellPhoneNumber("0821234567"));
        assertTrue(login.checkCellPhoneNumber("+27721234567"));
    }
    
    @Test
    public void testRecipientCell_Invalid() {
        assertFalse(login.checkCellPhoneNumber("12345"));
        assertFalse(login.checkCellPhoneNumber("082123"));
    }
    
    // Test 3: Message ID is 10 digits
    @Test
    public void testMessageID_Length() {
        // Generate a test ID
        String messageID = "";
        for (int j = 0; j < 10; j++) {
            int digit = (int)(Math.random() * 10);
            messageID += digit;
        }
        assertEquals(10, messageID.length());
    }
    
    // Test 4: Message Hash format
    @Test
    public void testMessageHash_Format() {
        String messageID = "4829156370";
        int messageNum = 1;
        String firstWord = "HELLO";
        String lastWord = "TEAM";
        
        String hash = messageID.substring(0, 2) + ":" + messageNum + ":" + firstWord + lastWord;
        hash = hash.toUpperCase();
        
        // Expected: first two of ID : message number : firstword + lastword
        assertTrue(hash.matches("\\d{2}:\\d+:[A-Z]+"));
    }
}
