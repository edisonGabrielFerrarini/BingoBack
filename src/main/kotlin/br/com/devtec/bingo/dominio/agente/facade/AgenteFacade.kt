package br.com.devtec.bingo.dominio.agente.facade

import br.com.devtec.bingo.dominio.agente.business.AgenteBusiness
import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import br.com.devtec.bingo.dominio.agente.dto.AgenteResponseDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.agente.model.entity.Agente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AgenteFacade(
    @Autowired
    val agenteBusiness: AgenteBusiness
) {

    fun create(agenteDTO: AgenteDTO): Agente {
        return agenteBusiness.create(agenteDTO)
    }

    fun getAll(): List<AgenteResponseDTO> {
        return agenteBusiness.getAll()
    }

    fun getById(id: Long): Agente {
        return agenteBusiness.getById(id)
    }

    fun getByCPF(cpf: String): Agente {
        return agenteBusiness.getByCPF(cpf)
    }

    fun vincularCliente(id_cliente: Long, id_agente: Long): ResponseEntity.BodyBuilder {
        agenteBusiness.vincularCliente(id_cliente, id_agente)
        return ResponseEntity.ok()
    }


}