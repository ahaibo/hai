package com.hai.mail.apache.commons;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by Administrator on 2017/10/8.
 */
public class SendEmailUtil {

    public static void sendSimpleEmail(String host, String protocol, String from, String to, String password) {

        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(host);
            email.setAuthentication(from, password);
            email.setSubject("subject");
            email.setFrom(from);
            email.addTo(to);
            email.setMsg("org.apache.commons.mail.simpleemail test...");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
