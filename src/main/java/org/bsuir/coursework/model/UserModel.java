package org.bsuir.coursework.model;

import lombok.Data;
import org.bsuir.coursework.entity.User;

import java.sql.Date;

@Data
public class UserModel {

    private String username;
    private Boolean isAdmin;
    private String lastname;
    private String name;
    private Date birthday;
    private String phone;
    private String role;

    public UserModel toModel(User user){
        username = user.getUsername();
        lastname = getLastname();
        name = user.getName();
        birthday = user.getBirthday();
        phone = user.getPhone();
        return this;
    }
}
