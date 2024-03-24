package com.shasank.resumeportfolio.service;

import com.shasank.resumeportfolio.model.EmailModel;

public interface EmailService {

    public void sendEmail(EmailModel emailModel);

    public void sendHtmlTemplate(EmailModel emailModel);
}
