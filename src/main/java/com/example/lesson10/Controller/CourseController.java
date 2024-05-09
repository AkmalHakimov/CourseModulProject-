package com.example.lesson10.Controller;

import com.example.lesson10.Config.ApiCall;
import com.example.lesson10.Db;
import com.example.lesson10.Entity.Course;
import com.example.lesson10.Payload.Request.ReqCourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.UUID;

@RestController
@RequestMapping(path = "/course")
public class CourseController {

    @PostMapping
    public ApiCall PostCourse(@RequestBody ReqCourse reqCourse){
        Course course = new Course(
                UUID.randomUUID(),
                reqCourse.getTitle(),
                reqCourse.getDescreption()
        );
        Db.courses.add(course);
        return new ApiCall(
                true,
                null,
                null
        );
    }

    @GetMapping
    public ApiCall GetCourses() throws FileNotFoundException {
        return new ApiCall(
                true,
                null,
                Db.courses
        );
//            FileReader fileReader = new FileReader("sdvsdv");
//            return ResponseEntity.ok(
//                    Db.courses
//            );

    }

    @DeleteMapping
    public ApiCall DeleteCourse(@RequestParam UUID id){
        for (Course course : Db.courses) {
            if(course.getId().equals(id)){
                Db.courses.remove(course);
                break;
            }
        }
        return new ApiCall(
                true,
                null,
                null
        );
    }

    @PutMapping("/{id}")
    public ApiCall EditCourse(@PathVariable UUID id,@RequestBody ReqCourse reqCourse){
        Course courseOld = new Course();
        for (Course course : Db.courses) {
            if(course.getId().equals(id)){
                courseOld = course;
            }
        }
        courseOld.setDescreption(reqCourse.getDescreption());
        courseOld.setTitle(reqCourse.getTitle());
        return new ApiCall(
                true,
                null,
                null
        );
    }
}
