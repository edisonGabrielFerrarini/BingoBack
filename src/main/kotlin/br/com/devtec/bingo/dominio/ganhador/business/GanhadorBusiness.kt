package br.com.devtec.bingo.dominio.ganhador.business

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
    lateinit var ganhadorRepository: GanhadorRepository

    fun create(numerosSorteados: String): List<Ganhador> {
        val tickets = ticketFacade.getByNumeros(numerosSorteados)
        if (tickets.isNotEmpty()){
            val ganhadores = tickets.asSequence().map {
                ganhadorRepository.save(
                    Ganhador(
                        ticket = it
                    )
                )
            }.toList()
            return ganhadores
        }
        return listOf()
    }

}