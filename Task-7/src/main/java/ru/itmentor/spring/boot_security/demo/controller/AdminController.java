package ru.itmentor.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/admin")
    public String viewMainUserPage(ModelMap model) {
        List<User> allUsers = userService.getAllUsers();
        List<Role> allRoles = roleService.getRoles();
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allRoles", allRoles);
        return "admin";
    }

    @PostMapping("/admin/user/create")
    public String createNewUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam("roleNames") List<String> roleNames,
                                @RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int age) {

        Set<Role> roles = new HashSet<>();

        for(String name : roleNames){
            roles.add(roleService.findByName(name).orElseThrow(() -> new NoSuchElementException(name)));
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(@RequestParam String username,
                             @RequestParam List<String> roleNames,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam int age,
                             @RequestParam Long id) {

        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);

        Set<Role> roleSet = new HashSet<>();
        for(String name : roleNames) {
            roleSet.add(roleService.findByName(name).orElseThrow(() -> new NoSuchElementException(name)));
        }
        user.setRoles(roleSet);

        userService.updateUser(user, id);
        return "redirect:/admin";
    }

}