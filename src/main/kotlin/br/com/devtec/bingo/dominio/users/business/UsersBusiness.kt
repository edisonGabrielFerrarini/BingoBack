package br.com.devtec.bingo.dominio.users.business

import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.dto.converter.toEntity
import br.com.devtec.bingo.dominio.users.model.repository.UserRepository
import br.com.devtec.bingo.security.services.UserDetailsImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.MessageDigest

@Service
class UsersBusiness : UserDetailsService {
    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var clienteFacade: ClienteFacade


    fun create(usersDTO: UsersDTO): ResponseEntity<ClienteResponseDTO> {
        val user = userRepository.save(usersDTO.toEntity())
        val senha = BCryptPasswordEncoder().encode(user.password)
        user.password = senha
        return clienteFacade.create(usersDTO.cliente, user)
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)

        return UserDetailsImpl(user)
    }

}