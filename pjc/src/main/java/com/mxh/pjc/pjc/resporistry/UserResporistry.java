package com.mxh.pjc.pjc.resporistry;

import com.mxh.pjc.pjc.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserResporistry {

    private ConcurrentHashMap<String,User> repository = new ConcurrentHashMap<String,User>();


    public Collection<User> findAll(){
        return  repository.values();
    }

    public boolean save(User user){
        String id = UUID.randomUUID().toString();
        user.setId(id);
        return repository.putIfAbsent(id,user) ==null;
    }

}
