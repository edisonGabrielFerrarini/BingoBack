package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository: JpaRepository<Ticket, Long> {
}