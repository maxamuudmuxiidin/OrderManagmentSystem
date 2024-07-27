package com.OrderManagementSystem.service;

import com.OrderManagementSystem.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
