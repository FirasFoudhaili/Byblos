package com.byblos.ticketapi.controller;

import com.byblos.ticketapi.mail.InboxReaderPOP3;
import com.byblos.ticketapi.model.Ticket;
import com.byblos.ticketapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    InboxReaderPOP3 inboxReaderPOP3;

    @GetMapping("/read")
    public ResponseEntity<Object> retrieveMessages() throws Exception {
        Message[] messages = inboxReaderPOP3.read();
        List<Ticket> tickets = new ArrayList<Ticket>();
        System.out.println(messages.length);

        for (Message message : messages) {
            try {
                Ticket _ticket = ticketRepository
                        .save(new Ticket(message.getSubject(), new Date(), "", "",
                                "", "", ""));
                tickets.add(_ticket);

            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


        return new ResponseEntity<>( tickets, HttpStatus.CREATED);



    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTutorials(@RequestParam(required = false) String objet) {
        try {
            List<Ticket> tickets = new ArrayList<Ticket>();

            if (objet == null)
                ticketRepository.findAll().forEach(tickets::add);
            else
                ticketRepository.findByObjetContaining(objet).forEach(tickets::add);

            if (tickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") long id) {
        Optional<Ticket> ticketData = ticketRepository.findById(id);

        if (ticketData.isPresent()) {
            return new ResponseEntity<>(ticketData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        try {
            Ticket _ticket = ticketRepository
                    .save(new Ticket(ticket.getObjet(), ticket.getDateDec(), ticket.getPerimetre(), ticket.getModule(),
                                     ticket.getFacturation(), ticket.getProfil(), ticket.getTdr()));
            return new ResponseEntity<>(_ticket, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        Optional<Ticket> ticketData = ticketRepository.findById(id);

        if (ticketData.isPresent()) {
            Ticket _ticket = ticketData.get();
            _ticket.setObjet(ticket.getObjet());
            _ticket.setDateDec(ticket.getDateDec());
            _ticket.setPerimetre(ticket.getPerimetre());
            _ticket.setModule(ticket.getModule());
            _ticket.setFacturation(ticket.getFacturation());
            _ticket.setProfil(ticket.getProfil());
            _ticket.setTdr(ticket.getTdr());
            return new ResponseEntity<>(ticketRepository.save(_ticket), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") long id) {
        try {
            ticketRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tickets")
    public ResponseEntity<HttpStatus> deleteAllTickets() {
        try {
            ticketRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
