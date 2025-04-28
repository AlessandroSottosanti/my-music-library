package org.lessons.spring.my_music_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping()
public class HomeController {
    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("currentUri", request.getRequestURI().equals("/") ? "/" : "");
        return "home";
    }
    
}
