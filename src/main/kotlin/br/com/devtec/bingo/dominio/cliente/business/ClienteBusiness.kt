package br.com.devtec.bingo.dominio.cliente.business

import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cliente.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cliente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.cliente.model.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Objects.nonNull

@Service
class ClienteBusiness{

    @Autowired
    lateinit var clienteRepository: ClienteRepository

    fun create(clienteRequestDTO: ClienteRequestDTO): Any {
        val cliente = clienteRepository.findByCpf(clienteRequestDTO.cpf)
        if (nonNull(cliente)){
            return ResponseEntity.status(400)
                .body("Não foi possivel cadastrar este cliente, pois já esta cadastrado.")
        }

        return clienteRepository.save(clienteRequestDTO.toEntity(saldo = 0.0, ganhos = 0.0)).toResponseDTO()
    }

    fun getAll(): Any {
        val allClientes = clienteRepository.findAll().asSequence().map {
            it.toResponseDTO()
        }.toList()

        if (allClientes.isNotEmpty()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(allClientes)
        }

        return ResponseEntity.status(400).body("não foi encontrado nenhum cliente")

    }

    fun getByCpf(cpf: String): Any? {
        val cliente = clienteRepository.findByCpf(cpf)?.toResponseDTO()

        if(nonNull(cliente)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteRepository.findByCpf(cpf)?.toResponseDTO())
        }

        return  ResponseEntity.status(400).body("cliente não encontrado.")
    }

    fun update(id: Long, clienteRequestDTO: ClienteRequestDTO): ResponseEntity<Any> {
        if (clienteRepository.existsById(id)){
            val save = clienteRepository.save(clienteRequestDTO.toEntity(id)).toResponseDTO()
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save)
        }

        return ResponseEntity.status(400).body("cliente não encontrado")
    }

    fun updateCredito(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        val cliente = clienteRepository.findById(id).get()
        if (nonNull(cliente)){
            val save = clienteRepository.save(cliente.copy(
                saldo = cliente.saldo + clienteSaldoDTO.saldo
            ))
            return if (nonNull(save)){
                ResponseEntity.status(HttpStatus.ACCEPTED).body(save)
            }else{
                ResponseEntity.status(400).body("erro ao salvar no banco de dados")
            }
        }
        return ResponseEntity.status(400).body("cliente não encontrado")
    }

}