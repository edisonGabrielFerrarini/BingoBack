package br.com.devtec.bingo.dominio.users.business

import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.facade.ClienteFacade
import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.dto.converter.toEntity
import br.com.devtec.bingo.dominio.users.model.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UsersBusiness {
    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var clienteFacade: ClienteFacade

    fun create(usersDTO: UsersDTO): ResponseEntity<ClienteResponseDTO> {
        val user = userRepository.save(usersDTO.toEntity())
        return clienteFacade.create(usersDTO.cliente, user)
    }

}