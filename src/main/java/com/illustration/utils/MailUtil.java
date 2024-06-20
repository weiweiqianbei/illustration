package com.illustration.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;
import java.util.Map;

public class MailUtil {
    private String from;        // 发件人
    private String to;          // 收件人
    private String subject;     // 标题
    private String text;        // 内容
    private String name;        // 匿名
    private boolean html;       // 是否支持html
    private Map<String, File> files;    // 附件

    private JavaMailSender javaMailSender;

    private SimpleMailMessage simpleMailMessage;
    private MimeMessageHelper mimeMessageHelper;

    public MailUtil() {
        this.javaMailSender = new JavaMailSenderImpl();
    }

    public MailUtil(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public MailUtil(String from, String to, String subject, String text, String name) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.name = "(" + name + ")";
    }

    public void setName(String name) {
        this.name = "(" + name + ")";
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public void setFiles(File file) {
        this.files.put(file.getName(), file);
    }

    public void setFiles(String fileName, File file) {
        this.files.put(fileName, file);
    }

    public void sendMail() {
        sendEmail(from, to, subject, text, name, false, files);
    }

    public void sendSimpleMail(String from, String to, String subject, String text) {
        sendSimpleMail(from, to, subject, text, name);
    }

    public void sendSimpleMail(String from, String to, String subject, String text, String name) {
        simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from + name);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

    public void sendEmail(String from, String to, String subject, String text) {
        sendEmail(from, to, subject, text, name, false, files);
    }

    public void sendEmail(String from, String to, String subject, String text, String name) {
        sendEmail(from, to, subject, text, name, false, files);
    }

    public void sendEmail(String from, String to, String subject, String text, String name, boolean html, Map<String, File> files) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from + name);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, html);
            for (Map.Entry<String, File> entry : files.entrySet()) {
                mimeMessageHelper.addAttachment(entry.getKey(), entry.getValue());
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
