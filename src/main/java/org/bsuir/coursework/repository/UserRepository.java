package org.bsuir.coursework.repository;

import org.bsuir.coursework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u Where u.username = :username")
    public User getUserByUsername(@Param("username") String username);

}
