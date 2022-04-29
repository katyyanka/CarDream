package org.bsuir.coursework.repository;

import org.bsuir.coursework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT u FROM User u Where u.username = :username")
    User getUserByUsername(@Param("username") String username);

    @Query( value = "select * " +
            "from USER " +
            "where USER.USERNAME not in (select DRIVER_EMAIL from vehicle)" +
            " AND USER.ROLE = 'ROLE_DRIVER'",
            nativeQuery = true)
    List<User> getUserDriverWithoutVehicle();


}
