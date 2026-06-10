package com.example.demo;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // 将来的にはDynamoDBに置き換わる
public class UserRepository {

    public final List<User> users = List.of(
        new User("Honda", 29),
        new User("Suzuki", 32),
        new User("Oyaizu", 32),
        new User("Fukuo", 32),
        new User("Mushi", 33)
    );

    public List<User> findAll() {
        return users;
    }

}