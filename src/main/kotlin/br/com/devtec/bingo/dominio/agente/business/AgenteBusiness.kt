package br.com.devtec.bingo.dominio.agente.business

import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.agente.dto.converter.toEntity
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

    fun create(agenteDTO: AgenteDTO): ResponseEntity<Any> {
        try {
            val gerente = gerenteFacade.getById(agenteDTO.id_gerente)
            if (gerente.isEmpty){
                return ResponseEntity.badRequest().body("Erro ao buscar Gerente")
            }
            val save = agenteRepository.save(agenteDTO.toEntity(gerente = gerente.get()))
            return ResponseEntity.accepted().body(save)
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao salvar dados")
        }
    }

    fun getAll(): ResponseEntity<List<AgenteDTO>> {
        try {
            val agentes = agenteRepository.findAll().map {
                it.toDTO()
            }
            return ResponseEntity.accepted().body(agentes)
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao buscar dados")
        }
    }

    fun getById(id: Long): AgenteDTO {
        try {
            return agenteRepository.findById(id).get().toDTO()
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao buscar dados")
        }
    }

    fun getByCPF(cpf: String): AgenteDTO {
        try {
            return agenteRepository.findByCpf(cpf).toDTO()
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao buscar dados")
        }
    }

    fun vincularCliente(id_cliente: Long, id_agente: Long): ResponseEntity.BodyBuilder {
        try {
            val agente = agenteRepository.findById(id_agente)
            val cliente = clienteFacade.getById(id_cliente)

            if (agente.isEmpty || cliente == null){
                throw PersistirDadosException("Erro ao buscar agente ou cliente")
            }

            agenteClienteRepository.save(ClienteAgente(
                agente = agente.get(),
                cliente = cliente
            ))

            return ResponseEntity.ok()
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao persistir dados")
        }
    }
}