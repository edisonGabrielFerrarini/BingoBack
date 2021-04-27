package br.com.devtec.bingo.dominio.ticket.model.repository

import br.com.devtec.bingo.dominio.ticket.model.entity.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findByClienteId(cliente_id: Long) : List<Ticket>?
    fun findByNumeros(numeros: String): List<Ticket>
}