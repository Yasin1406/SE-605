package org.example;

public class AuthManager {
    protected UserRepository userRepository;
    protected HashLib hashLib;
    public AuthManager(UserRepository userRepository, HashLib hashLib) {
        this.userRepository = userRepository;
        this.hashLib = hashLib;
    }
    protected User login(String email, String password) {
        return userRepository.findByEmail(email);
    }
}
