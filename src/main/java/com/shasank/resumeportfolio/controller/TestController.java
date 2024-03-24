package com.shasank.resumeportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    String getTest(Model model) {
        model.addAttribute("from", "John Doe");
        model.addAttribute("subject", "This is a test subject");
        model.addAttribute("message", "This is a test message");
        return "message";
    }
}
