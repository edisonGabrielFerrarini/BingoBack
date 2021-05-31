package br.com.devtec.bingo.dominio.users.facade

import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.users.business.UsersBusiness
import br.com.devtec.bingo.dominio.users.dto.UsersAdminDTO
import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.dto.UsersLoginDTO
import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UsersFacade(
    @Autowired private val usersBusiness: UsersBusiness
) {

    fun createCliente(usersDTO: UsersDTO): ResponseEntity<ClienteResponseDTO> {
        return usersBusiness.create(usersDTO)
    }

    fun createAdmin(usersAdminDTO: UsersAdminDTO){
        usersBusiness.createAdmin(usersAdminDTO)
    }

    fun login(usersLoginDTO: UsersLoginDTO): ResponseEntity<Any> {
        return usersBusiness.login(usersLoginDTO)
    }

    fun loginAdmin(): ResponseEntity<String> {
        return usersBusiness.loginAdmin()
    }

}