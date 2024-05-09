package com.example.lesson10.Payload.Request;

import com.example.lesson10.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqSecurity {
    private String url;
    private String method;
    private List<Role> roles;
}
