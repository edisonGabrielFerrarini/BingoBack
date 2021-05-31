package br.com.devtec.bingo.api.users

import br.com.devtec.bingo.dominio.users.dto.UsersAdminDTO
import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.dto.UsersLoginDTO
import br.com.devtec.bingo.dominio.users.facade.UsersFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UsersApi {

    @Autowired
    private lateinit var usersFacade: UsersFacade

    @PostMapping("/cadastro")
    fun create(@RequestBody usersDTO: UsersDTO): ResponseEntity<Any> {
        return ResponseEntity.ok(usersFacade.createCliente(usersDTO))
    }

    @PostMapping
    fun createAdmin(@RequestBody usersAdminDTO: UsersAdminDTO): ResponseEntity.BodyBuilder {
        return try {
            usersFacade.createAdmin(usersAdminDTO)
            ResponseEntity.accepted()
        }catch (e: Exception){
            ResponseEntity.badRequest()
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody usersLoginDTO: UsersLoginDTO): ResponseEntity<Any> {
        return usersFacade.login(usersLoginDTO)
    }

    @GetMapping("/login_admin")
    fun loginAdmin(): ResponseEntity<String> {
        return usersFacade.loginAdmin()
    }


}