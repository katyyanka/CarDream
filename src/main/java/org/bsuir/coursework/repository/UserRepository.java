package org.bsuir.coursework.repository;

import org.bsuir.coursework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
