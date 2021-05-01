package br.com.devtec.bingo.api.users

import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.facade.UsersFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UsersApi {

    @Autowired
    private lateinit var usersFacade: UsersFacade

    @PostMapping("/cadastro")
    fun create(@RequestBody usersDTO: UsersDTO): ResponseEntity<Any> {
        println(usersDTO)
        return ResponseEntity.ok(usersFacade.createCliente(usersDTO))
    }

}