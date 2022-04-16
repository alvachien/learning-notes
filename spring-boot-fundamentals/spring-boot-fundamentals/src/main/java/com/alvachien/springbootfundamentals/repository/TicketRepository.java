package com.alvachien.springbootfundamentals.repository;

import com.alvachien.springbootfundamentals.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
