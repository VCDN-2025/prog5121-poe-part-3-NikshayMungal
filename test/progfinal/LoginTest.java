/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package progfinal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jessica Mungal
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of checkUsername method, of class Login.
     */
      @Test
    public void testCorrectUsernameFormat() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1")); // Valid
    }

    @Test
    public void testIncorrectUsernameFormat() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!!")); // Invalid
    }

    @Test
    public void testCorrectPasswordComplexity() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!")); // Valid
    }

    @Test
    public void testIncorrectPasswordComplexity() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password")); // Invalid
    }

    @Test
    public void testCorrectCellPhoneFormat() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976")); // Valid
    }

    @Test
    public void testIncorrectCellPhoneFormat() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553")); // Invalid
    }

    @Test
    public void testRegisterUserSuccess() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User successfully registered.", result);
    }

    @Test
    public void testRegisterUserFailUsername() {
        Login login = new Login();
        String result = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserFailPassword() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterUserFailCell() {
        Login login = new Login();
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testLoginSuccess() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailure() {
        Login login = new Login();
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login();
        String message = login.returnLoginStatus(true, "Kyle", "Brown");
        assertEquals("Welcome Kyle, Brown it is great to see you again.", message);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        Login login = new Login();
        String message = login.returnLoginStatus(false, "", "");
        assertEquals("Username or password incorrect, please try again.", message);
    }
}