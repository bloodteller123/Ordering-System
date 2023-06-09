package com.zihanc.takeout.utils;

public class Storage {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    private static Long currentUserId;

    public static void setId(Long id){
        currentUserId = id;
    }

    public static Long getId(){
        return currentUserId;
    }
}
