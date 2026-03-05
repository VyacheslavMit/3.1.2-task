package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    void updateUser(User user, Long id);
    void removeUser(Long id);
}
