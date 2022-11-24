package com.security.services;

import com.security.exceptions.UserNotFoundException;
import com.security.model.User;
import com.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IService{

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new UserNotFoundException("Cannot find user with id: " + id));
    }

    @Override
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User userToUpdate = repository.findById(id).orElseThrow(()-> new UserNotFoundException("Cannot find user with id: " + id));
        userToUpdate.setName(updatedUser.getName());
        userToUpdate.setEmail(updatedUser.getEmail());
        userToUpdate.setPassword(updatedUser.getPassword());
        return repository.save(userToUpdate);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
