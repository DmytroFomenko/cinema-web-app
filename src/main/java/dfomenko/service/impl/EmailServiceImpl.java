package dfomenko.service.impl;

import dfomenko.service.EmailService;
import dfomenko.utils.HtmlUtils;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.Map;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailCinemaAddr;

    @Value("${spring.mail.pseudonym}")
    private String emailCinemaPseudonym;

    @Value("${app.email-output-to-console}")
    private boolean emailOutputToConsole;

    @Autowired
    private HtmlUtils htmlUtils;


    private void outEmailToConsole(String emailTo,
                                   String subject,
                                   boolean haveAttachments,
                                   String text) {
        System.out.println("Email:");
        System.out.println("  From email: " + emailCinemaAddr);
        System.out.println("  From pseudonym: " + emailCinemaPseudonym);
        System.out.println("  To: " + emailTo);
        System.out.println("  Subject: " + subject);
        System.out.println("  Attachments present: " + haveAttachments);
        System.out.println("  Message text: \n" + text);

    }

    @Override
    public void sendSimpleMessage(String emailTo,
                                  String subject,
                                  String text) {

        if (emailOutputToConsole) {
            outEmailToConsole(emailTo, subject, false, text);
            return;
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailCinemaAddr);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        emailSender.send(mailMessage);
    }


    @Override
    public void sendMessageWithAttachment(String emailTo,
                                          String subject,
                                          String text,
                                          boolean isHtml,
                                          Map<String, Path> attachments) throws MessagingException {

        boolean isMultipart = attachments != null;

        if (emailOutputToConsole) {
            outEmailToConsole(emailTo, subject, isMultipart, text);
            return;
        }

        try {
            MimeMessage emailMessage = emailSender.createMimeMessage();

            MimeMessageHelper emailHelper = new MimeMessageHelper(emailMessage, isMultipart);

            Address address = new InternetAddress(emailCinemaAddr, emailCinemaPseudonym, "utf-8");

            emailMessage.setFrom(address);
            emailHelper.setTo(emailTo); // setRecipients(MimeMessage.RecipientType.TO, emailTo);
            emailHelper.setSubject(subject);
            emailHelper.setText(text, isHtml); // setContent(, "text/html; charset=utf-8");

            if (isMultipart) {
                for (Map.Entry<String, Path> entry : attachments.entrySet()) {
                    String name = entry.getKey();
                    Path path = entry.getValue();
                    FileSystemResource file = new FileSystemResource(path.toFile());
                    if (file.exists()) {
                        emailHelper.addAttachment(name, file);
                    }
                }
            }

            emailSender.send(emailMessage);

        } catch (UnsupportedEncodingException e) {
            throw new MessagingException("E-mail address is not valid", e);
        }
    }

    @Override
    public void sendTemplatedMessage(String emailTo,
                                     String subject,
                                     String templateFilePath,
                                     Map<String, Path> attachments,
                                     Map<String, Object> parameters) throws MessagingException {

        // Build new mail Html
        String htmlText = htmlUtils.makeHtmlFromTemplate(templateFilePath, parameters);

        this.sendMessageWithAttachment(emailTo, subject, htmlText, true, attachments);

    }
}

