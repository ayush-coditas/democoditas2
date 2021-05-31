package com.coditas.democoditas2.service.impl;

import com.coditas.democoditas2.pojo.EmailHeaders;
import com.coditas.democoditas2.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.Instant;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value("${toEmail}")
    private String toEmail;

    @Override
    public void sendEmail(String filePath) {
        log.info("Executing sendEmail request at: {}", Instant.now());
        log.info("Setting Email Headers at: {}", Instant.now());
        EmailHeaders emailHeaders = new EmailHeaders();
        emailHeaders.setTo(toEmail);
        emailHeaders.setBody("Please find the attached workbook of Employee Data.\nThis E-mail has been sent from DemoCoditas");
        emailHeaders.setSubject("Employee Data Workbook - DemoCoditas");
        File file = new File(filePath);
        emailHeaders.setAttachment(file);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if (emailHeaders.getAttachment().exists() && !emailHeaders.getAttachment().isDirectory()) {
            try {
                log.info("Setting Helper at: {}", Instant.now());
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setTo(emailHeaders.getTo());
                helper.setSubject(emailHeaders.getSubject());
                helper.setText(emailHeaders.getBody());
                helper.addAttachment("EmployeeDataCsvWorkbook.csv", emailHeaders.getAttachment());
            } catch (Exception ex) {
                log.info("Failed to send Email to {}", emailHeaders.getTo());
            }
        }
        log.info("Sending Email at {}", Instant.now());
        javaMailSender.send(mimeMessage);
    }
}
