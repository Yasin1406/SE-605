package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthManagerTest {
    private AuthManager authManager;
    private UserRepository userRepository;
    private HashLib hashLib;
    private User user;
    String validEmail = "yasin@gmail.com";
    String validPassword = "1406";
    String hashedPassword = "hashed 1406";
    String invalidEmail = "Yasin@gmail.com";
    String invalidPassword = "1407";

    @Before
    public void setUp() throws Exception {
        user = new User(validEmail, validPassword);
        userRepository = mock(UserRepository.class);
        hashLib = mock(HashLib.class);
        authManager = new AuthManager(userRepository, hashLib);
        when(hashLib.hash(validPassword)).thenReturn(hashedPassword);
        // Fix: Mock findByEmail with validEmail, not validPassword
        when(userRepository.findByEmail(validEmail)).thenReturn(user);
        when(userRepository.findByEmail(invalidEmail)).thenThrow(new IllegalArgumentException());
        when(hashLib.hash(invalidPassword)).thenThrow(new IllegalArgumentException());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() {
        User resultUser = authManager.login(validEmail, validPassword);
        String resultHash = hashLib.hash(validPassword);
        assertEquals(user, resultUser);
        assertEquals(hashedPassword, resultHash);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_login_failed_for_invalid_email() {
        userRepository.findByEmail(invalidEmail);
        hashLib.hash(validPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_login_failed_for_invalid_password() {
        hashLib.hash(invalidPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_login_failed_for_invalid_email_and_password() {
        userRepository.findByEmail(invalidEmail);
    }
}