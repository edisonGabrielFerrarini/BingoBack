package br.com.devtec.bingo.api.cliente

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/cliente"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ClientesApi(
) {

    @GetMapping(value = ["/all"])
    fun getAllClients(): String {
        return "ola a todos"
    }

    @GetMapping(value = ["/get/{id}"])
    fun get(@PathVariable("id") id: Int): String {
        return "$id"
    }
}