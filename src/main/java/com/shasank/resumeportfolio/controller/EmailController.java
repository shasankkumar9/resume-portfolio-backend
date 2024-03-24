package com.shasank.resumeportfolio.controller;

import com.shasank.resumeportfolio.model.EmailModel;
import com.shasank.resumeportfolio.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/text")
    public ResponseEntity<String> sendMail(@RequestBody EmailModel emailModel) {
        emailService.sendEmail(emailModel);
        return ResponseEntity.ok("Message Queued!");
    }

    @PostMapping("/template")
    public ResponseEntity<String> sendHtmlTemplateMessage(@RequestBody EmailModel emailModel) {
        emailService.sendHtmlTemplate(emailModel);
        return ResponseEntity.ok("Message Queued!");
    }

}
