package org.bsuir.coursework.repository;

import org.bsuir.coursework.model.User;
import org.bsuir.coursework.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

@InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    User user;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUsername("user@gmail.com");
        user.setPassword("1234");
    }

    @Test
    public void testGetUser() {

        when(userRepository.getUserByUsername(anyString())).thenReturn(user);
        User userMock = userRepository.getUserByUsername("user@gmail.com");
        Assertions.assertEquals("user@gmail.com", userMock.getUsername());
    }


}
