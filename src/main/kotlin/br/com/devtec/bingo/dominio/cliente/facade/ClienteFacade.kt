package br.com.devtec.bingo.dominio.cliente.facade

import br.com.devtec.bingo.dominio.cliente.business.ClienteBusiness
import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClienteFacade {
    @Autowired
    lateinit var clienteBusiness: ClienteBusiness

    fun create(clienteDTO: ClienteRequestDTO, users: Users): ResponseEntity<ClienteResponseDTO> {
        return clienteBusiness.create(clienteDTO, users)
    }

    fun getAll(pageable: Pageable): Page<ClienteResponseDTO> {
        return clienteBusiness.getAll(pageable)
    }

    fun getById(id: Long): Cliente {
        return clienteBusiness.getByID(id)
    }

    fun getByCPF(cpf: String): ResponseEntity<ClienteResponseDTO> {
        return clienteBusiness.getByCPF(cpf)
    }

    fun buscarPorID(id: Long, isNotAdmin: Boolean = true): Any? {
        return clienteBusiness.buscarPorID(id, isNotAdmin)
    }

    fun update(id: Long, clienteDTO: ClienteRequestDTO): ResponseEntity<ClienteResponseDTO> {
        return clienteBusiness.update(id, clienteDTO)
    }

    fun updateSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<ClienteResponseDTO> {
        return clienteBusiness.updateSaldo(id, clienteSaldoDTO)
    }


    fun debitarSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        return clienteBusiness.debitarSaldo(id, clienteSaldoDTO)
    }


    fun getByUser(users: Users): Cliente {
        return clienteBusiness.getByUser(users)
    }
}