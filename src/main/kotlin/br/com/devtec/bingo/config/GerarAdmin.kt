package br.com.devtec.bingo.config

import br.com.devtec.bingo.dominio.users.dto.UsersAdminDTO
import br.com.devtec.bingo.dominio.users.facade.UsersFacade
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class GerarAdmin(
    val usersFacade: UsersFacade
){

    @PostConstruct
    fun criar(){
        usersFacade.createAdmin(UsersAdminDTO(
            email = "adminGameSena@Hotmail.com.br",
            senha = "0f1324de378fb2e399bc66843abb736ca47eb638b6a05bacb23a82efb5ffd62b"
        ))
    }

}