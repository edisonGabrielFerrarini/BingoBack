package br.com.devtec.bingo.api.ticket

import br.com.devtec.bingo.dominio.ticket.dto.TicketDTO
import br.com.devtec.bingo.dominio.ticket.facade.TicketFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/ticket"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TicketApi(
    @Autowired private val ticketFacade: TicketFacade
) {

    @PostMapping(value = ["/create"])
    fun create(@RequestBody ticketDTO: TicketDTO): Any {
        return ticketFacade.create(ticketDTO)
    }

    @GetMapping(value = ["/all"])
    fun getAll(): ResponseEntity<Any> {
        return ticketFacade.getAll()
    }

    @GetMapping(value = ["/get/{id}"])
    fun getByCliente(@PathVariable("id") id: Long ): ResponseEntity<Any> {
        return ticketFacade.getByCliente(id)
    }
}