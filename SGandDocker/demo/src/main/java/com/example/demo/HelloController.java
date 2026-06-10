package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Repository;
// import org.springframework.web.server.ResponseStatusException;

// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {

    private final UserRepository repository;

    public HelloController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot";
    }

    @GetMapping("/time")
    public String time() {
        return LocalDateTime.now().toString();
    }

    @GetMapping("/version")
    public String version() {
        return "version 1.0";
    }

    @GetMapping("/user")
    public User user() {
        return new User("Honda", 29);
    }

    @GetMapping("/user/{name}")    // HTTPを受け取る
    public ResponseEntity<?> userByName(@PathVariable String name) {
        for(User user: repository.findAll()){   // 検索処理
            if(user.getName().equals(name)){
                return ResponseEntity.ok(user);    // JSON 返却
            }
        }

        // return null;
        // リクエストされたユーザーが存在しない場合は404エラー返却
        // throw new ResponseStatusException(
        //     HttpStatus.NOT_FOUND,
        //     "User Not Found: " + name
        // );
        return ResponseEntity.status(404)
               .body(new ErrorResponse("User Not Found", name));
    }

    @GetMapping("/users")
    public ResponseEntity<?> findUser(@RequestParam String name) {
        for(User user: repository.findAll()){
          if(user.getName().equals(name)){
              return ResponseEntity.ok(user);
          }
        }

        // return null;
        // リクエストされたユーザーが存在しない場合は404エラー返却
        //throw new ResponseStatusException(
        //    HttpStatus.NOT_FOUND,
        //    "User Not Found: " + name
        //);
        // ResponseEntityによりerrorを返す
        return ResponseEntity.status(404)
               .body(new ErrorResponse("User not Found: ", name));
    }
}
