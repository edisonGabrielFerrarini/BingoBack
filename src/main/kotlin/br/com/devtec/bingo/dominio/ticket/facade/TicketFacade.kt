package br.com.devtec.bingo.dominio.ticket.facade

import br.com.devtec.bingo.dominio.ticket.business.TicketBusiness
import br.com.devtec.bingo.dominio.ticket.dto.TicketDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TicketFacade {

    @Autowired
    lateinit var ticketBusiness: TicketBusiness

    fun create(ticketDTO: TicketDTO): Any {
        return ticketBusiness.create(ticketDTO)
    }

    fun getAll(): ResponseEntity<Any> {
        return ticketBusiness.getAll()
    }

    fun getByCliente(id: Long): ResponseEntity<Any> {
        return ticketBusiness.getByUser(id)
    }

}