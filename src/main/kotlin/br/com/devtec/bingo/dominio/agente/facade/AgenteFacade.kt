package br.com.devtec.bingo.dominio.agente.facade

import br.com.devtec.bingo.dominio.agente.business.AgenteBusiness
import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AgenteFacade(
    @Autowired
    val agenteBusiness: AgenteBusiness
) {

    fun create(agenteDTO: AgenteDTO): ResponseEntity<Any> {
        return  agenteBusiness.create(agenteDTO)
    }

    fun getAll(): ResponseEntity<List<AgenteDTO>> {
        return agenteBusiness.getAll()
    }

    fun getById(id: Long): ResponseEntity<AgenteDTO> {
        return ResponseEntity.accepted().body(agenteBusiness.getById(id))
    }

    fun getByCPF(cpf: String): ResponseEntity<AgenteDTO> {
        return ResponseEntity.accepted().body(agenteBusiness.getByCPF(cpf))
    }

    fun vincularCliente(id_cliente: Long, id_agente: Long): ResponseEntity.BodyBuilder {
        return agenteBusiness.vincularCliente(id_cliente, id_agente)
    }


}