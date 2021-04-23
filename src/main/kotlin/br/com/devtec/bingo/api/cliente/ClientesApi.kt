package br.com.devtec.bingo.api.cliente

import br.com.devtec.bingo.dominio.bingo.dto.ClienteDTO
import br.com.devtec.bingo.dominio.bingo.facade.ClienteFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/cliente"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientesApi(
    @Autowired private val clienteFacade: ClienteFacade
) {

    @PostMapping(value = ["/create"])
    fun create(@RequestBody clienteDTO: ClienteDTO){
        clienteFacade.create(clienteDTO)
    }

    @GetMapping(value = ["/all"])
    fun getAllClients(): String {
        return "clienteFacade.create()"
    }

    @GetMapping(value = ["/get/{id}"])
    fun get(@PathVariable("id") id: Int): String {
        return "$id"
    }
}