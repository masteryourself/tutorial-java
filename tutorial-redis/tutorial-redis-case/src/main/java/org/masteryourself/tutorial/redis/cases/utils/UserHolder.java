package org.masteryourself.tutorial.redis.cases.utils;


import org.masteryourself.tutorial.redis.cases.domain.User;

public class UserHolder {

    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user) {
        tl.set(user);
    }

    public static User getUser() {
        return tl.get();
    }

    public static void removeUser() {
        tl.remove();
    }

}
