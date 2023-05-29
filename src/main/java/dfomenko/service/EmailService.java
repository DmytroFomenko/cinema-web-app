package dfomenko.service;


import jakarta.mail.MessagingException;

import java.nio.file.Path;
import java.util.Map;


public interface EmailService {

    void sendSimpleMessage(String emailTo,
                           String subject,
                           String text);

    void sendMessageWithAttachment(String emailTo,
                                   String subject,
                                   String text,
                                   boolean isHtml,
                                   Map<String, Path> attachments) throws MessagingException;

    void sendTemplatedMessage(String emailTo,
                              String subject,
                              String templateFilePath,
                              Map<String, Path> attachments,
                              Map<String, Object> parameters) throws MessagingException;
}