package br.com.devtec.bingo.dominio.users.business

import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.users.dto.UsersAdminDTO
import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.dto.UsersLoginDTO
import br.com.devtec.bingo.dominio.users.dto.converter.toEntity
import br.com.devtec.bingo.dominio.users.model.entity.Users
import br.com.devtec.bingo.dominio.users.model.repository.UserRepository
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class UsersBusiness {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var clienteFacade: ClienteFacade


    fun create(usersDTO: UsersDTO): ResponseEntity<ClienteResponseDTO> {
        try {
            val user = userRepository.save(
                usersDTO.toEntity(
                    BCryptPasswordEncoder().encode(usersDTO.senha)
                )
            )
            return clienteFacade.create(usersDTO.cliente, user)
        } catch (e: Exception) {
            throw PersistirDadosException("erro ao salvar usuario")
        }
    }

    fun createAdmin(usersAdminDTO: UsersAdminDTO) {
        val save = userRepository.save(
            usersAdminDTO.toEntity(
                BCryptPasswordEncoder().encode(usersAdminDTO.senha)
            )
        )
    }

    fun login(usersLoginDTO: UsersLoginDTO): ResponseEntity<Any> {
        val user = SecurityContextHolder.getContext().authentication.principal as Users
        val cliente = clienteFacade.getByUser(user)
        return ResponseEntity.ok().body(cliente.toResponseDTO())
    }

    fun loginAdmin(): ResponseEntity<String> {
        val user = SecurityContextHolder.getContext().authentication.principal as Users
        return ResponseEntity.ok().body(user.email)
    }


}

























