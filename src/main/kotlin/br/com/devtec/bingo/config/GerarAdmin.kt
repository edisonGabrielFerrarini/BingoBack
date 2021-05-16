package br.com.devtec.bingo.config

import br.com.devtec.bingo.dominio.users.dto.UsersAdminDTO
import br.com.devtec.bingo.dominio.users.facade.UsersFacade
import br.com.devtec.bingo.dominio.users.model.entity.Roles
import br.com.devtec.bingo.dominio.users.model.repository.RolesRepository
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class GerarAdmin(
    val usersFacade: UsersFacade,
    val rolesRepository: RolesRepository
){

    @PostConstruct
    fun criar(){
        rolesRepository.save(Roles(
            nomeRole = "ROLE_USER"
        ))

        rolesRepository.save(Roles(
            nomeRole = "ROLE_ADMIN"
        ))

        usersFacade.createAdmin(UsersAdminDTO(
            email = "adminGameSena@Hotmail.com.br",
            senha = "0f1324de378fb2e399bc66843abb736ca47eb638b6a05bacb23a82efb5ffd62b"
        ))
    }

}