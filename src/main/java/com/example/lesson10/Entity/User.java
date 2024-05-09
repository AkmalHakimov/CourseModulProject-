package com.example.lesson10.Entity;

import com.example.lesson10.Payload.Request.LoginReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Role role;


    public boolean checkUser(LoginReq loginReq){
        return loginReq.getEmail().equals(email) && loginReq.getPassword().equals(password);
    }
}
