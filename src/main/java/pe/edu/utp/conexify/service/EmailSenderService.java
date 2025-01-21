package pe.edu.utp.conexify.service;

import org.apache.commons.mail.EmailException;

public interface EmailSenderService {
    void sendEmail(String recipientEmail, String name, String code) throws EmailException;
    String createHtmlMessage(String name, String code);
}
