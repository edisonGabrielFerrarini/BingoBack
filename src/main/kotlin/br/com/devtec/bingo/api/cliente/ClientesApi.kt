package br.com.devtec.bingo.api.cliente

import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/cliente"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientesApi(
    @Autowired private val clienteFacade: ClienteFacade
) {

    @PostMapping(value = ["/create"])
    fun create(@RequestBody clienteRequestDTO: ClienteRequestDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(clienteFacade.create(clienteRequestDTO))
    }

    @GetMapping(value = ["/all"])
    fun getAllClients(): ResponseEntity<Any> {
        return ResponseEntity.ok(clienteFacade.getAll())
    }

    @GetMapping(value = ["/get/{cpf}"])
    fun getByCpf(@PathVariable("cpf") cpf: String): ResponseEntity<Any> {
        return ResponseEntity.ok(clienteFacade.getByCpf(cpf))
    }

    @PutMapping(value = ["/update/{id}"])
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody clienteRequestDTO: ClienteRequestDTO
    ): ResponseEntity<Any> {
        return clienteFacade.update(id, clienteRequestDTO)
    }


}