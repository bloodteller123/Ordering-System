package com.zihanc.takeout.utils;

import com.zihanc.takeout.model.Employee;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUtil {
    private static final RandomNumberGenerator rnd = new SecureRandomNumberGenerator();

    private static final String hashing = "SHA-256";

    public static void encrypt(Employee user){
        // randomly generate data for salting
//        https://auth0.com/blog/adding-salt-to-hashing-a-better-way-to-store-passwords/#Generating-a-Good-Random-Salt
        String salts = rnd.nextBytes().toString();
        // add salts to randomize hashing
        String newPass = new SimpleHash(hashing, user.getPassword(), salts, 1).toString();
        user.setPassword(newPass);
        // need to save salt
        user.setSalt(salts);
    }

    public static String encryptPassword(String password, String salt){
        String newPassword = new SimpleHash(hashing, password, salt, 1).toString();
        return newPassword;
    }
}