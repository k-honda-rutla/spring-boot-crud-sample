package com.example.demo;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // 追加 POST API追加対応
import org.springframework.web.bind.annotation.DeleteMapping; // 追加 DELETE API追加対応
import org.springframework.web.bind.annotation.PutMapping; // 追加 UPDATE API追加対応
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody; // 追加 POST API追加対応
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
//import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

@RestController
public class HelloController {

    private final UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
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
        User user = userService.findByName(name);

        if (user != null) {
            return ResponseEntity.ok(user);
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
        User user = userService.findByName(name);

        if (user != null) {
            return ResponseEntity.ok(user);
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

    // 追加START POST API追加対応
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
    // 追加END   POST API追加対応

    // 追加START DELETE API追加対応
    @DeleteMapping("/user/{name}")
    public ResponseEntity<?> deleteUser(@PathVariable String name) {
        boolean deleted = userService.deleteByName(name);

        if(deleted) {
            return ResponseEntity.ok()
                        .body("Deleted：" + name);
        }

        return ResponseEntity.status(404)
                        .body(new ErrorResponse("User Not Found: ",  name));
       }
    // 追加END   DELETE API追加対応

    // 追加START UPDATE API追加対応
    @PutMapping("/user/{name}")
    public ResponseEntity<?> updateUser(@PathVariable String name, 
                                        @RequestBody User user) {
        User updatedUser = userService.updateByName(name, user);

        if(updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.status(404)
                        .body(new ErrorResponse("User Not Found: ", name));
    }
    // 追加END   UPDATE API追加対応





}
