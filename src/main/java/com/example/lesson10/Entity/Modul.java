package com.example.lesson10.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Modul {
    private UUID id;
    private String title;
    private String descreption;
    private UUID courseId;
}
