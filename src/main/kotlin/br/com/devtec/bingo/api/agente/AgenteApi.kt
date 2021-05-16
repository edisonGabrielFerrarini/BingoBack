package br.com.devtec.bingo.api.agente

import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import br.com.devtec.bingo.dominio.agente.facade.AgenteFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/agente"], produces = [MediaType.APPLICATION_JSON_VALUE])
class AgenteApi(
    @Autowired val agenteFacade: AgenteFacade
) {

    @PostMapping
    fun create(@RequestBody agenteDTO: AgenteDTO): ResponseEntity<Any> {
        return agenteFacade.create(agenteDTO)
    }

    @GetMapping
    fun getAll(): ResponseEntity<List<AgenteDTO>> {
        return agenteFacade.getAll()
    }

    @GetMapping(value = ["/id/{id}"])
    fun getById(@PathVariable("id") id: Long): ResponseEntity<AgenteDTO> {
        return agenteFacade.getById(id)
    }

    @GetMapping(value = ["/cpf/{cpf}"])
    fun getByCpf(@PathVariable("cpf") cpf: String): ResponseEntity<AgenteDTO> {
        return agenteFacade.getByCPF(cpf)
    }

    @GetMapping(value = ["/vincula/cliente/{id_cliente}/agente/{id_agente}"])
    fun vincularCliente(
            @PathVariable("id_cliente") id_cliente: Long,
            @PathVariable("id_agente") id_agente: Long
    ): ResponseEntity.BodyBuilder {
        return agenteFacade.vincularCliente(id_cliente, id_agente)
    }


}