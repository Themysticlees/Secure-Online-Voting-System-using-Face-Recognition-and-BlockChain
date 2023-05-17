package net.codejava.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public boolean sendEmail(String subject, String message, String to) {
        boolean f = false;
        String from = "donotreplysovs@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        System.out.println("Properties" + properties);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("donotreplysovs@gmail.com", "hcnzzzanqlgdyhbn");
            }

        });

        session.setDebug(true);

        // step 2 : compose message
        MimeMessage m = new MimeMessage(session);

        try {
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            m.setSubject(subject);
        //    m.setText(message);

            m.setContent(message,"text/html");

            // step 3 : send message
            Transport.send(m);
            System.out.println("sent success");
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;

    }
}
