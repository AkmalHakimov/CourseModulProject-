package com.example.lesson10.Controller;


import com.example.lesson10.Db;
import com.example.lesson10.Entity.ApiCall;
import com.example.lesson10.Entity.User;
import com.example.lesson10.Payload.Request.LoginReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ApiCall Login(@RequestBody LoginReq loginReq){
        for (User user : Db.users) {
            if(user.checkUser(loginReq)){
                return new ApiCall(
                        true,
                        user.getId().toString(),
                        null
                );
            }
        }
        return new ApiCall(
                false,
                "Login yoki Parol Xato!!!",
                null
        );
    }
}
