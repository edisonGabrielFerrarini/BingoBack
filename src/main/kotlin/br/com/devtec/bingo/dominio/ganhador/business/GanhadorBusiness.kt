package br.com.devtec.bingo.dominio.ganhador.business

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.ganhador.dto.GanhadorDTO
import br.com.devtec.bingo.dominio.ganhador.dto.converter.toDTO
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador
import br.com.devtec.bingo.dominio.ganhador.model.repository.GanhadorRepository
import br.com.devtec.bingo.dominio.ticket.facade.TicketFacade
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.math.ceil

@Service
class GanhadorBusiness {

    @Autowired
    lateinit var ticketFacade: TicketFacade

    @Autowired
    lateinit var clienteFacede: ClienteFacade

    @Autowired
    lateinit var ganhadorRepository: GanhadorRepository

    fun getAll(pageable: Pageable): Page<GanhadorDTO> {
        try {
            val ganhadores = ganhadorRepository.findAll(pageable).map {
                it.toDTO()
            }
            return ganhadores
        }catch (e: Exception){
            throw PersistirDadosException(e.message!!)
        }
    }

    fun create(numerosSorteados: String, cartela: Cartela): List<Ganhador> {
        val tickets = ticketFacade.getByNumerosAndCartela(numerosSorteados, cartela)
        if (tickets != null) {
            if (tickets.isNotEmpty()) {
                val ganhadores = tickets.asSequence().map {
                    ganhadorRepository.save(
                        Ganhador(
                            ticket = it
                        )
                    )
                }.toList()


                val saldo = ceil(cartela.valor).toInt() / ganhadores.size
                ganhadores.forEach {
                    clienteFacede.updateSaldo(
                        it.ticket.cliente.id, ClienteSaldoDTO(
                            saldo = saldo.toDouble()
                        )
                    )
                }

                return ganhadores
            }
        }
        return listOf()
    }


}