package com.example.lesson10;

import com.example.lesson10.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Db {
    public static List<Course> courses = new ArrayList<>();
    public static List<Modul> moduls = new ArrayList<>();
    public static List<Lesson> lessons = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    public static void Init(){
        User user = new User(
                UUID.randomUUID(),
                "student",
                "1",
                "1",
                "1",
                Role.ROLE_USER
        );User user1 = new User(
                UUID.randomUUID(),
                "manager",
                "1",
                "2",
                "2",
                Role.ROLE_ADMIN
        );User user2 = new User(
                UUID.randomUUID(),
                "superAdmin",
                "1",
                "3",
                "3",
                Role.ROLE_SUPERADMIN
        );

        users.add(user);
        users.add(user1);
        users.add(user2);
    }
}
