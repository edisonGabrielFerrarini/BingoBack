package br.com.devtec.bingo.api.cliente

import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/cliente"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientesApi(
    @Autowired private val clienteFacade: ClienteFacade
) {

    @GetMapping
    fun getAllClients(pageable: Pageable): ResponseEntity<Page<ClienteResponseDTO>> {
        return ResponseEntity.ok(clienteFacade.getAll(pageable))
    }

    @GetMapping(value = ["/busca/{id}"])
    fun getByID(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(clienteFacade.buscarPorID(id))
    }

    @GetMapping(value = ["/admin/busca/{id}"])
    fun getByIDAdmin(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return ResponseEntity.ok(clienteFacade.buscarPorID(id, false))
    }

    @PutMapping(value = ["/update/{id}"])
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody clienteRequestDTO: ClienteRequestDTO
    ): ResponseEntity<ClienteResponseDTO> {
        return clienteFacade.update(id, clienteRequestDTO)
    }

    @PutMapping(value = ["/update/saldo/{id}"])
    fun updateCreditos(
        @PathVariable("id") id: Long,
        @RequestBody clienteSaldoDTO: ClienteSaldoDTO
    ): ResponseEntity<ClienteResponseDTO> {
        return clienteFacade.updateSaldo(id, clienteSaldoDTO)
    }

}