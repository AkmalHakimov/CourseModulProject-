package com.example.lesson10.Config;

import com.example.lesson10.Db;
import com.example.lesson10.Entity.Course;
import com.example.lesson10.Entity.Role;
import com.example.lesson10.Entity.User;
import com.example.lesson10.Payload.Request.ReqSecurity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
public class Filter extends OncePerRequestFilter {
    List<ReqSecurity> securities = List.of(
            new ReqSecurity("/course", "post", List.of(Role.ROLE_ADMIN, Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/course", "put", List.of(Role.ROLE_ADMIN, Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/course", "delete", List.of(Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/modul", "post", List.of(Role.ROLE_USER, Role.ROLE_ADMIN, Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/modul", "put", List.of(Role.ROLE_ADMIN, Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/modul", "delete", List.of(Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/lesson", "post", List.of(Role.ROLE_ADMIN, Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/lesson", "put", List.of(Role.ROLE_ADMIN, Role.ROLE_SUPERADMIN)),
            new ReqSecurity("/lesson", "delete", List.of(Role.ROLE_SUPERADMIN))
    );

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String method = request.getMethod();
        String uuid = request.getHeader("key");
        User currentUser = findUserById(uuid);
        if (hasPermission(url, method, currentUser)) {
            filterChain.doFilter(request, response);
        } else {
            throw new Exception("Damingni ol");
        }
    }

    private boolean hasPermission(String url, String method, User currentUser) {
        int count = 0;
        for (ReqSecurity security : securities) {
            if (security.getUrl().equalsIgnoreCase(url) && security.getMethod().equalsIgnoreCase(method)) {
                count++;
            }
        }

        if (count > 0) {
            if (currentUser == null) {
                return false;
            }
            for (ReqSecurity security : securities) {
                if (security.getUrl().equals(url) && security.getMethod().equalsIgnoreCase(method)) {
                    if (security.getRoles().contains(currentUser.getRole())) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            return true;
        }
    }

    private User findUserById(String uuid) {
        for (User user : Db.users) {
            if (user.getId().toString().equals(uuid)) {
                return user;
            }
        }
        return null;
    }

//    private boolean hasPermission(String url, String method, Course course) {
//        int count = 0;
//        for (ReqSecurity security : securities) {
//            if(security.getUrl().equals(url) && security.getMethod().equals(method)){
//                count++;
//            }
//        }
//        if(count>0){
//            if(course==null){
//                return false;
//            }
//            for (ReqSecurity security : securities) {
//                if(security.getUrl().equals(url) && security.getMethod().equalsIgnoreCase(method)){
//                    if(security.getRoles().contains(course)){
//
//                    }
//                }
//            }
//        }
//        return true;
//    }
//
//    private Course findCourseById(String courseId) {
//        for (Course cours : Db.courses) {
//            if(cours.getId().toString().equals(courseId)){
//                return cours;
//            }
//        }
//        return null;
//    }
}
