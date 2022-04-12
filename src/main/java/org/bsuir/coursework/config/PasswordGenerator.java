package org.bsuir.coursework.config;

import org.bsuir.coursework.entity.Vehicle;
import org.bsuir.coursework.repository.VehicleRepository;
import org.bsuir.coursework.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "12345";
        System.out.println(bCryptPasswordEncoder.encode(password));

    }
}
