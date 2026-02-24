package com.example.Hustlers.service;

import com.example.Hustlers.dto.EmailDetails;

public interface EmailServiceInterface {

    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
