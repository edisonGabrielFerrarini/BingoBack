package br.com.devtec.bingo.dominio.bingo.business

import br.com.devtec.bingo.dominio.bingo.dto.ClienteDTO
import br.com.devtec.bingo.dominio.bingo.dto.converter.toEntity
import br.com.devtec.bingo.dominio.bingo.model.repository.AgenteRepository
import br.com.devtec.bingo.dominio.bingo.model.repository.ClienteRepository
import br.com.devtec.bingo.dominio.bingo.model.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClienteBusiness(
    @Autowired private val clienteRepository: ClienteRepository,
    @Autowired private val agenteRepository: AgenteRepository,
    @Autowired private val usuarioRepository: UsuarioRepository
){

    fun create(clienteDTO: ClienteDTO){
        val agente = agenteRepository.findById(clienteDTO.id_agente)
        val usuario = usuarioRepository.findById(clienteDTO.id_usuario)
        val salvou = clienteRepository.save(clienteDTO.toEntity(
                agente = agente,
                usuario = usuario
            )
        )

        println("salvou aqui doiod $salvou")
    }

}