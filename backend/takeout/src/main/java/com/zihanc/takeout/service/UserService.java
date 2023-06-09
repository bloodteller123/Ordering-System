package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Employee;
import com.zihanc.takeout.model.User;

import java.util.Optional;

public interface UserService {
    User findById(Long id);

    User save(User user);
}
