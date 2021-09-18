package com.byblos.ticketapi;

import com.byblos.ticketapi.mail.InboxReaderPOP3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.Pop3MailReceiver;

@SpringBootApplication
public class TicketApiApplication implements CommandLineRunner {

    @Autowired
    InboxReaderPOP3 inboxReaderPOP3;

    public static void main(String[] args) {
        SpringApplication.run(TicketApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        inboxReaderPOP3.read();
    }
}
