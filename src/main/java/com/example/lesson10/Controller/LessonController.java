package com.example.lesson10.Controller;

import com.example.lesson10.Config.ApiCall;
import com.example.lesson10.Db;
import com.example.lesson10.Entity.Lesson;
import com.example.lesson10.Entity.Modul;
import com.example.lesson10.Payload.Request.ReqLesson;
import com.example.lesson10.Payload.Request.ReqModul;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/lesson")
public class LessonController {
    @PostMapping("/{modulId}")
    public ApiCall PostLesson(@RequestBody ReqLesson reqLesson, @PathVariable UUID modulId){
        Modul modul = FindModulById(modulId);
        Lesson lesson = new Lesson(
                UUID.randomUUID(),
                reqLesson.getTitle(),
                reqLesson.getDescription(),
                modulId
        );
        Db.lessons.add(lesson);
        return new ApiCall(
                true,
                null,
                null
        );
    }

    private Modul FindModulById(UUID modulId){
        for (Modul modul : Db.moduls) {
            if(modul.getId().equals(modulId)){
                return modul;
            }
        }
        return null;
    }

    @GetMapping("/{modulId}")
    public ApiCall getLessons(@PathVariable UUID modulId){
        List<Lesson> result = new ArrayList<>();
        for (Lesson lesson : Db.lessons) {
            if(lesson.getModulId().equals(modulId)){
                result.add(lesson);
            }
        }
        return new ApiCall(
                true,
                null,
                result
        );
    }

    @DeleteMapping("/{lessonId}")
    public ApiCall DeleteLesson(@PathVariable UUID lessonId){
        for (Lesson lesson : Db.lessons) {
            if(lesson.getId().equals(lessonId)){
                Db.moduls.remove(lesson);
                break;
            }
        }
        return new ApiCall(
                true,
                null,
                null
        );
    }

    @PutMapping("/{lessonId}")
    public ApiCall EditModul(@PathVariable UUID lessonId, @RequestBody ReqLesson reqLesson){
        Lesson oldLesson = new Lesson();
        for (Lesson lesson : Db.lessons) {
            if(lesson.getId().equals(lessonId)){
                oldLesson = lesson;
            }
        }
        oldLesson.setDescription(reqLesson.getDescription());
        oldLesson.setTitle(reqLesson.getTitle());

        return new ApiCall(
                true,
                null,
                null
        );
    }
}
