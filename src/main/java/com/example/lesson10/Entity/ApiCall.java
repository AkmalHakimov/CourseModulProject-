package com.example.lesson10.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCall {
    private boolean success;
    private String message;
    private Object object;
}
