package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.User;
import com.zihanc.takeout.repository.UserRepo;
import com.zihanc.takeout.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(Long id) {

        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }
}
