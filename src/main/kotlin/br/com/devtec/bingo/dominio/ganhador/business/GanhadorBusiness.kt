package br.com.devtec.bingo.dominio.ganhador.business

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador
import br.com.devtec.bingo.dominio.ganhador.model.repository.GanhadorRepository
import br.com.devtec.bingo.dominio.ticket.facade.TicketFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GanhadorBusiness {

    @Autowired
    lateinit var ticketFacade: TicketFacade

    @Autowired
    lateinit var clienteFacede: ClienteFacade

    @Autowired
    lateinit var ganhadorRepository: GanhadorRepository

    fun create(numerosSorteados: String, cartela: Cartela): List<Ganhador> {
        val tickets = ticketFacade.getByNumerosAndCartela(numerosSorteados, cartela)
        if (tickets != null) {
            if (tickets.isNotEmpty()){
                val ganhadores = tickets.asSequence().map {
                    ganhadorRepository.save(
                        Ganhador(
                            ticket = it
                        )
                    )
                }.toList()

                ganhadores.forEach {
                    val ganhos = it.ticket.cartela.valor / ganhadores.size
                    clienteFacede.updateGanhos(it.ticket.cliente.id, ClienteGanhosDTO(ganhos))
                }

                return ganhadores
            }
        }
        return listOf()
    }


}