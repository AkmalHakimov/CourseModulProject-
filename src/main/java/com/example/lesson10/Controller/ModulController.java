package com.example.lesson10.Controller;

import com.example.lesson10.Config.ApiCall;
import com.example.lesson10.Db;
import com.example.lesson10.Entity.Course;
import com.example.lesson10.Entity.Modul;
import com.example.lesson10.Payload.Request.ReqModul;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/modul")
public class ModulController {

    @PostMapping("/{courseId}")
    public ApiCall PostModul(@PathVariable UUID courseId, @RequestBody ReqModul reqModul){
        Course course = FindCourseById(courseId);
        assert course != null;
        Modul modul = new Modul(
                UUID.randomUUID(),
                reqModul.getTitle(),
                reqModul.getDescreption(),
                course.getId()
        );
        Db.moduls.add(modul);
        return new ApiCall(
                true,
                null,
                null
        );
    }

    private Course FindCourseById(UUID courseId){
        for (Course cours : Db.courses) {
            if(cours.getId().equals(courseId)){
                return cours;
            }
        }
        return null;
    }

    @GetMapping("/{courseId}")
    public ApiCall getModuls(@PathVariable UUID courseId){
        List<Modul> result = new ArrayList<>();
        for (Modul modul : Db.moduls) {
            if(modul.getCourseId().equals(courseId)){
                result.add(modul);
            }
        }
        return new ApiCall(
                true,
                null,
                result
        );
    }

    @DeleteMapping("/{modulId}")
    public ApiCall DeleteModul(@PathVariable UUID modulId){
        for (Modul modul : Db.moduls) {
            if(modul.getId().equals(modulId)){
                Db.moduls.remove(modul);
                break;
            }
        }
        return new ApiCall(
                true,
                null,
                null
        );
    }

    @PutMapping("/{modulId}")
    public ApiCall EditModul(@PathVariable UUID modulId, @RequestBody ReqModul reqModul){
        Modul oldModul = new Modul();
        for (Modul modul : Db.moduls) {
            if(modul.getId().equals(modulId)){
                oldModul = modul;
            }
        }
        oldModul.setDescreption(reqModul.getDescreption());
        oldModul.setTitle(reqModul.getTitle());

        return new ApiCall(
                true,
                null,
                null
        );
    }
}
