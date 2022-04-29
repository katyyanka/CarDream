package org.bsuir.coursework.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "12345";
        System.out.println(bCryptPasswordEncoder.encode(password));

    }
}
