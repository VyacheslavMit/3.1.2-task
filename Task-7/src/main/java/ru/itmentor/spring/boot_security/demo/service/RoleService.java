package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void add(Role role);
    Optional<Role> findByName(String name);
    List<Role> getRoles();
}
