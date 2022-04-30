package org.bsuir.coursework.repository;

import org.bsuir.coursework.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class MyRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    MyRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @BeforeEach
    void initUseCase() {
        List<User> users = Arrays.asList(
                new User("user0@gmail.com", "123456")
        );

        userRepository.saveAll(users);
    }

    @AfterEach
    public void destroyAll(){
        userRepository.deleteAll();
    }

    @Test
    public void saveAll_success() {
        List<User> users = Arrays.asList(
                new User("user1@gmail.com", "123456"),
                new User("user2@gmail.com", "123456"),
                new User("user3@gmail.com", "123456")

        );
        Iterable<User> allUsers = userRepository.saveAll(users);

        AtomicInteger validIdFound = new AtomicInteger();
        allUsers.forEach(user -> {
            if(user.getUsername()!=null){
                validIdFound.getAndIncrement();
            }
        });

        Assert.assertEquals(validIdFound.intValue(),users.size());
    }

    @Test
    public void findAll_success() {
        List<User> allUsers = userRepository.findAll();
        Assert.assertTrue(allUsers.size()>(1));
    }
}