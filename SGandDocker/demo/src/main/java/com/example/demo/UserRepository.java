package com.example.demo;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;

@Repository // 将来的にはDynamoDBに置き換わる
public class UserRepository {

    // 変更START POST API追加対応
    // リストに追加(POST)できるよう、ArrayList化
    //public final List<User> users = List.of(
    public final List<User> users 
    // 変更END   POST API追加対応
    = new ArrayList<>( List.of(
        new User("Honda", 29),
        new User("Suzuki", 32),
        new User("Oyaizu", 32),
        new User("Fukuo", 32),
        new User("Mushi", 33)
    )
    );

    public List<User> findAll() {
        return users;
    }

    public User findByName(String name) {

        for(User user: users){
            if(user.getName().equals(name)){
                return user;
            }
        }

        // リクエストされたユーザーが存在しない場合はnullを返却
        // nullを返却すると、Controllerで404エラーを返却するように設定している
        return null;
    }

    // 追加START POST API追加対応
    public User save(User user) {
        users.add(user);
        return user;
    }
    // 追加END   POST API追加対応

    // 追加START DELETE API追加対応
    public boolean deleteByName(String name) {
        // 削除できればTrue, できなければFalse
        return users.removeIf(user -> user.getName().equals(name));
    }
    // 追加END   DELETE API追加対応
    
    // 追加START UPDATE API追加対応
    public User updateByName(String name, User newUser) {
        for ( User user : users ) {
            if(user.getName().equals(name) ) {
                user.setName(newUser.getName());
                user.setAge(newUser.getAge());
                return user;
            }
        }

        return null;
    }
    // 追加START END API追加対応


}