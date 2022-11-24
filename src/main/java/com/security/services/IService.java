package com.security.services;

import com.security.model.User;

import java.util.List;

public interface IService {

    public User findById(Long id);

    public List<User> findAllUsers();

    public User createUser(User user);

    public User updateUser(Long id, User updatedUser);

    public boolean deleteUser(Long id);
}
