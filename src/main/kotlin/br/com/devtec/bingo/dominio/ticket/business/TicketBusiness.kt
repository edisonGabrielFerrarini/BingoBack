package br.com.devtec.bingo.dominio.ticket.business

import br.com.devtec.bingo.dominio.cartela.facade.CartelaFacade
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import br.com.devtec.bingo.dominio.ticket.dto.TicketDTO
import br.com.devtec.bingo.dominio.ticket.dto.TicketResponseDTO
import br.com.devtec.bingo.dominio.ticket.dto.converter.toDTO
import br.com.devtec.bingo.dominio.ticket.model.entity.Ticket
import br.com.devtec.bingo.dominio.ticket.model.repository.TicketRepository
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TicketBusiness(
    @Autowired private val clienteFacade: ClienteFacade,

    @Autowired private val cartelaFacade: CartelaFacade,

    @Autowired private val ticketRepository: TicketRepository
) {


    fun create(ticketDTO: TicketDTO): Any {
        try {
            val cliente = clienteFacade.getById(ticketDTO.id_cliente).body as Cliente
            val cartela = cartelaFacade.getByAtiva()


            if (cliente != null && cartela != null) {
                val valorTotal = cartela.valor_numero * 20

                val statusCode = clienteFacade.debitarSaldo(
                    id = cliente.id,
                    clienteSaldoDTO = ClienteSaldoDTO(
                        saldo = valorTotal
                    )
                ).statusCode

                return if (statusCode == HttpStatus.ACCEPTED) {
                    val save = ticketRepository.save(
                        Ticket(
                            numeros = ticketDTO.numeros,
                            cliente = cliente,
                            valor = valorTotal,
                            cartela = cartela
                        )
                    )

                    save.toDTO()
                } else {
                    "saldo insuficiente"
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma cartela foi encontrada")
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    fun getAll(pageable: Pageable): ResponseEntity<List<TicketResponseDTO>> {
        try {
            val tickets = ticketRepository.findAll(pageable).asSequence().map {
                it.toDTO()
            }.toList()
            return ResponseEntity.accepted().body(tickets)
        }catch (e: Exception){
            throw PersistirDadosException("erro ao buscar dados")
        }
    }

    fun getByUser(idCliente: Long): ResponseEntity<Any> {
        var tickets = ticketRepository.findByClienteId(idCliente)
        if (tickets != null) {
            val ticketsDTO = tickets.asSequence().map { it.toDTO() }.toList()

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ticketsDTO)
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao encontrar ticket")
    }

    fun getByNumeros(numerosSorteados: String, cartela: Cartela): List<Ticket>? {
        try {
            return ticketRepository.findByNumerosAndCartelaId(numerosSorteados, cartela)
        }catch (e: Exception){
            throw Exception(e)
        }
    }

}