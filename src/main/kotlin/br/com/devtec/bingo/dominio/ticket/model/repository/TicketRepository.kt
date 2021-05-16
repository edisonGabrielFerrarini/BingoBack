package br.com.devtec.bingo.dominio.ticket.model.repository

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.ticket.model.entity.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findByClienteId(cliente_id: Long) : List<Ticket>?

    @Query("SELECT t FROM Ticket t JOIN FETCH t.cartela WHERE t.numeros = ?1 AND t.cartela = ?2")
    fun findByNumerosAndCartelaId(numeros: String, cartela: Cartela): List<Ticket>?


}