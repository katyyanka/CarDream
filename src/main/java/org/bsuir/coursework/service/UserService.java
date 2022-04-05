package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.User;
import org.bsuir.coursework.model.UserModel;
import org.bsuir.coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String id) {
        return userRepository.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword((new BCryptPasswordEncoder()).encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
