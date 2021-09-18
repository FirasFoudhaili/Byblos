package com.byblos.ticketapi.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import java.util.Properties;

interface InboxReader {
    void setup() throws Exception;
    Message[] read() throws Exception;
}

@Service
public class InboxReaderPOP3 implements InboxReader {

    @Value("${mail.username}")
    String username;
    @Value("${mail.password}")
    String password;
    @Value("${mail.server}")
    String server;
    @Value("${mail.port}")
    String port;


    Properties properties = new Properties();
    Folder emailFolder;
    Store store;

    @PostConstruct
    public void setup() throws Exception {
        properties.put("mail.pop3.host", server);
        properties.put("mail.pop3.port", port);
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        store = emailSession.getStore("pop3s");
        store.connect(server, username, password);
        emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        System.out.println("Inbox Type: " + emailFolder.getType());
    }

    public Message[] read() throws Exception {
        Message[] messages = emailFolder.getMessages();
        emailFolder.close(false);
        emailFolder.open(Folder.READ_ONLY);
        System.out.println( "Messages Length: " + messages.length);
        for (Message message : messages) {
            System.out.println("--------------------------------");
            System.out.println("From: " + message.getFrom()[0]);
            System.out.println("Subject " + message.getSubject());

        }
        return messages;
    }
}
