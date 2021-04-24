package br.com.devtec.bingo.dominio.cliente.facade

import br.com.devtec.bingo.dominio.cliente.business.ClienteBusiness
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClienteFacade {
    @Autowired
    lateinit var clienteBusiness: ClienteBusiness

    fun create(clienteDTO: ClienteRequestDTO): Any {
        return clienteBusiness.create(clienteDTO)
    }

    fun getAll(): Any {
        return clienteBusiness.getAll()
    }

    fun getByCpf(cpf: String): Any? {
        return clienteBusiness.getByCpf(cpf)
    }

    fun update(id: Long, clienteDTO: ClienteRequestDTO): ResponseEntity<Any> {
        return clienteBusiness.update(id, clienteDTO)
    }
}