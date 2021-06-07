package br.com.devtec.bingo.dominio.agente.business

import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import br.com.devtec.bingo.dominio.agente.dto.AgenteResponseDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toEntity
import br.com.devtec.bingo.dominio.agente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.agente.model.entity.Agente
import br.com.devtec.bingo.dominio.agente.model.entity.ClienteAgente
import br.com.devtec.bingo.dominio.agente.model.repository.AgenteRepository
import br.com.devtec.bingo.dominio.agente.model.repository.ClienteAgenteRepository
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.gerente.facade.GerenteFacade
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AgenteBusiness(
    @Autowired private val agenteRepository: AgenteRepository,
    @Autowired private val gerenteFacade: GerenteFacade,
    @Autowired private val clienteFacade: ClienteFacade,
    @Autowired private val agenteClienteRepository: ClienteAgenteRepository
) {

    fun create(agenteDTO: AgenteDTO): Agente {
        val gerente = gerenteFacade.getById(agenteDTO.id_gerente)
        return agenteRepository.save(agenteDTO.toEntity(gerente = gerente.get()))
    }

    fun getAll(): List<AgenteResponseDTO> {
        return agenteRepository.findAll().map {
            it.toResponseDTO()
        }
    }

    fun getById(id: Long): Agente {
        return agenteRepository.findById(id).get()
    }

    fun getByCPF(cpf: String): Agente {
        return agenteRepository.findByCpf(cpf)
    }

    fun vincularCliente(id_cliente: Long, id_agente: Long){
        val agente = agenteRepository.findById(id_agente)
        val cliente = clienteFacade.getById(id_cliente) ?: throw PersistirDadosException("Erro ao buscar agente ou cliente")
        agenteClienteRepository.save(
            ClienteAgente(
                agente = agente.get(),
                cliente = cliente
            )
        )
    }
}