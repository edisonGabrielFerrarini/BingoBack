package br.com.devtec.bingo.api.agente

import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import br.com.devtec.bingo.dominio.agente.dto.AgenteResponseDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toResponseDTO
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
    fun create(@RequestBody agenteDTO: AgenteDTO) =
        try {
            ResponseEntity.accepted().body(agenteFacade.create(agenteDTO).toResponseDTO())
        }catch (e: Exception){
            ResponseEntity.badRequest().body("Erro ao buscar Gerente")
        }

    @GetMapping
    fun getAll() =
        try {
            ResponseEntity.accepted().body(agenteFacade.getAll())
        }catch (e: Exception){
            ResponseEntity.badRequest().body("Erro ao buscar todos agentes")
        }


    @GetMapping(value = ["/id/{id}"])
    fun getById(@PathVariable("id") id: Long) =
        try {
            ResponseEntity.accepted().body(agenteFacade.getById(id).toDTO())
        }catch (e: Exception){
            ResponseEntity.badRequest().body("Erro ao buscar agente")
        }

    @GetMapping(value = ["/cpf/{cpf}"])
    fun getByCpf(@PathVariable("cpf") cpf: String) =
        try {
            ResponseEntity.accepted().body(agenteFacade.getByCPF(cpf).toDTO())
        }catch (e: Exception){
            ResponseEntity.badRequest().body("Erro ao buscar agente")
        }


    @GetMapping(value = ["/vincula/cliente/{id_cliente}/agente/{id_agente}"])
    fun vincularCliente(
            @PathVariable("id_cliente") id_cliente: Long,
            @PathVariable("id_agente") id_agente: Long
    ) =
        try {
            agenteFacade.vincularCliente(id_cliente, id_agente)
        }catch (e: Exception){
            ResponseEntity.badRequest().body("Erro ao vincular cliente com agente")
        }

}