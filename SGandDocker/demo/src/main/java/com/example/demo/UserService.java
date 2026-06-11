package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByName(String name) {
        return repository.findByName(name);
    }
    // 追加START POST API追加対応
    public User save(User user) {
        return repository.save(user);
    }
    // 追加END   POST API追加対応

    // 追加START DELETE API追加対応
    public boolean deleteByName(String name) {
        return repository.deleteByName(name);
    }
    // 追加END   DELETE API追加対応

    // 追加START UPDATE API追加対応
    public User updateByName(String name, User newUser)  {
        return repository.updateByName(name, newUser);
    }
    // 追加END   UPDATE API追加対応

}
