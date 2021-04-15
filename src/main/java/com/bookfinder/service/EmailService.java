package com.bookfinder.service;

import com.bookfinder.dto.custom.WatchlistBookDTO;
import com.bookfinder.enums.EmailType;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.mailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    @SneakyThrows
    public void sendMail(String recipientEmail, WatchlistBookDTO watchlistBookDTO, EmailType emailType){
        mailSender.send(buildEmail(recipientEmail,watchlistBookDTO,emailType));
    }

    private MimeMessage buildEmail(String recipientEmail,WatchlistBookDTO watchlistBookDTO, EmailType emailType) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("bookify@localhost.com");
        mimeMessageHelper.setSubject(emailType.getEmailSubject());
        mimeMessageHelper.setTo(recipientEmail);
        mimeMessageHelper.setText(getEmailTemplate(watchlistBookDTO,emailType),true);
        return mimeMessage;
    }

    private String getEmailTemplate(WatchlistBookDTO watchlistBookDTO, EmailType emailType){
        final Context context = new Context();
        context.setVariable("watchlistBook", watchlistBookDTO);
        return templateEngine.process(emailType.getEmailTemplateName(),context);
    }
}
