package org.bsuir.coursework.service;

import org.bsuir.coursework.entity.User;
import org.bsuir.coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Нет пользователя с такими логином!");
        }
        return new MyUserDetails(user);
    }
}
