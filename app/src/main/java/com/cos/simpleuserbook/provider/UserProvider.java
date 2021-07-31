package com.cos.simpleuserbook.provider;

import com.cos.simpleuserbook.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserProvider {

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        for(int i=0; i<10; i++){
            users.add(new User("bori"+i, "0101234567"+i, "https://www.notion.so/jungspin/Android-375d6e2e454f42efbdd60620258dbdf7"));
        }
        return users;
    }
}
