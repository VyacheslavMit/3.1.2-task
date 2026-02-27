package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String printHomePage(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Добро пожаловать на главную страницу");
        messages.add("Для того что бы продолжить, пожалуйста, перейдите по ссылке");
        model.addAttribute("messages", messages);
        return "index";
    }
}