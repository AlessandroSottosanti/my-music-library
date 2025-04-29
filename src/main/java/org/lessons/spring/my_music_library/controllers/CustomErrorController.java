package org.lessons.spring.my_music_library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController {
    @GetMapping("/403")
    public String accessDenied() {
        return "error/403";
    }
}
