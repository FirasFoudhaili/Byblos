package com.byblos.ticketapi.repository;

import com.byblos.ticketapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {


    List<Ticket> findByObjet(String objet);

    List<Ticket> findByObjetContaining(String objet);

}
