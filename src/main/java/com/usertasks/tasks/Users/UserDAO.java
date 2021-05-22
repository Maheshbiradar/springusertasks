package com.usertasks.tasks.Users;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAO {

    private static List<User> users = new ArrayList<>();
    private static  int count = 3;

    static {
        users.add(new User( 1,"Mahesh", 30, new Date(), "test@123.com"));
        users.add(new User(2, "Jack", 30, new Date(), "test@123.com"));
        users.add(new User(3,"Jane", 30, new Date(), "test@123.com"));
    }


    public List<User> getAllUsers() {
        return users;
    }
    public User getUserById(int id) {
        for(User user: users) {
            if(user.getId() == id){
                return user;
            }
        }
        return  null;
    }
    public User saveUser(User user) {
        if(user.getId() ==  null) {
            user.setId(++count);
        }
        users.add(user);
        return user;

    }
    public User deleteUserById(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return  null;
    }

}
