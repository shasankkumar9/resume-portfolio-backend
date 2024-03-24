package com.shasank.resumeportfolio.service.impl;

import com.shasank.resumeportfolio.model.EmailModel;
import com.shasank.resumeportfolio.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${my.email}")
    private String myEmail;

    @Value("${my.name}")
    private String myName;

    @Value("${my.subject}")
    private String mySubject;

    @Value("${my.mail.template}")
    private String mailTemplate;

    @Override
    @Async
    public void sendEmail(EmailModel emailModel) {

        log.info("Email Model: {}", emailModel);

        try {
            MimeMessage mail = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);

            String subject = emailModel.getSubject().isBlank() ? mySubject : emailModel.getSubject();
            String message = "<h4>Message From: " + emailModel.getFrom() + "</h4>" + "<p>" + emailModel.getMessage() + "</p>";

            helper.setFrom(myEmail, myName);
            helper.setTo(myEmail);
            helper.setSubject(StringUtils.capitalize(subject));
            helper.setText(message, true);

            emailSender.send(mail);
            log.info("Email Queued!");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlTemplate(EmailModel emailModel) {
        log.info("Email Model: {}", emailModel);

        try {
            MimeMessage mail = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);

            String subject = emailModel.getSubject().isBlank() ? mySubject : emailModel.getSubject();

            helper.setFrom(myEmail, myName);
            helper.setTo(myEmail);
            helper.setSubject(StringUtils.capitalize(subject));

            Context context = new Context();
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("from", emailModel.getFrom());
            attributes.put("message", emailModel.getMessage());

            context.setVariables(attributes);

            String html = templateEngine.process(mailTemplate, context);

            helper.setText(html, true);

            log.info("HTML: {}", html);

            emailSender.send(mail);
            log.info("Email Queued!");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }

}
