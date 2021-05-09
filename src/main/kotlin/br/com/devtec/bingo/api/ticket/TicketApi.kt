package br.com.devtec.bingo.api.ticket

import br.com.devtec.bingo.dominio.ticket.dto.TicketDTO
import br.com.devtec.bingo.dominio.ticket.dto.TicketResponseDTO
import br.com.devtec.bingo.dominio.ticket.facade.TicketFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://192.168.0.11:8081"])
@RequestMapping(value = ["/api/ticket"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TicketApi(
    @Autowired private val ticketFacade: TicketFacade
) {

    @PostMapping
    fun create(@RequestBody ticketDTO: TicketDTO): Any {
        return ticketFacade.create(ticketDTO)
    }

    @GetMapping
    fun getAll(pageable: Pageable): ResponseEntity<Page<TicketResponseDTO>> {
        return ticketFacade.getAll(pageable)
    }

    @GetMapping(value = ["/busca/{id}"])
    fun getByCliente(@PathVariable("id") id: Long ): ResponseEntity<Any> {
        return ticketFacade.getByCliente(id)
    }
}