package com.example.Hustlers.service.serviceImplementation;

import com.example.Hustlers.dto.EmailDetails;
import com.example.Hustlers.service.EmailServiceInterface;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServiceInterface {
    private final JavaMailSender javaMailSender;
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String sender) {
        this.javaMailSender = javaMailSender;
        this.sender = sender;
    }

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());
            mailMessage.setText(details.getMsgBody());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessege = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessegeHelper;
        try {
            mimeMessegeHelper = new MimeMessageHelper(mimeMessege, true);
            mimeMessegeHelper.setFrom(sender);
            mimeMessegeHelper.setTo(details.getRecipient());
            mimeMessegeHelper.setText(details.getMsgBody());
            mimeMessegeHelper.setSubject(details.getSubject());

            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

            mimeMessegeHelper.addAttachment(file.getFilename(),file);
            javaMailSender.send(mimeMessege);
        }
        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
        return "Mail sent successfully with attachment!!";
    }
}
