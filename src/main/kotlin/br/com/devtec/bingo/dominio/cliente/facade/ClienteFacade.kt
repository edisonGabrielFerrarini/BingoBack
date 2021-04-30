package br.com.devtec.bingo.dominio.cliente.facade

import br.com.devtec.bingo.dominio.cliente.business.ClienteBusiness
import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClienteFacade {
    @Autowired
    lateinit var clienteBusiness: ClienteBusiness

    fun create(clienteDTO: ClienteRequestDTO): Any {
        return clienteBusiness.create(clienteDTO)
    }

    fun getAll(pageable: Pageable): ResponseEntity<List<ClienteResponseDTO>> {
        return clienteBusiness.getAll(pageable)
    }

    fun getById(id: Long): ResponseEntity<Any> {
        return clienteBusiness.getByID(id)
    }

    fun getByCpf(cpf: String): Any? {
        return clienteBusiness.getByCpf(cpf)
    }

    fun update(id: Long, clienteDTO: ClienteRequestDTO): ResponseEntity<Any> {
        return clienteBusiness.update(id, clienteDTO)
    }

    fun updateSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        return clienteBusiness.updateSaldo(id, clienteSaldoDTO)
    }

    fun updateGanhos(id: Long, clienteGanhosDTO: ClienteGanhosDTO): ResponseEntity<Any> {
        return clienteBusiness.updateGanhos(id, clienteGanhosDTO)
    }

    fun debitarSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        return clienteBusiness.debitarSaldo(id, clienteSaldoDTO)
    }

    fun debitarGanhos(id: Long, clienteGanhosDTO: ClienteGanhosDTO): ResponseEntity<Any> {
        return clienteBusiness.debitarGanhos(id, clienteGanhosDTO)
    }
}