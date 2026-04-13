package com.chat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    Login login = new Login();
    
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27838968974"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
