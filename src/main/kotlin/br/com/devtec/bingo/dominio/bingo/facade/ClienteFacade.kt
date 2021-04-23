package br.com.devtec.bingo.dominio.bingo.facade

import br.com.devtec.bingo.dominio.bingo.business.ClienteBusiness
import br.com.devtec.bingo.dominio.bingo.dto.ClienteDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClienteFacade(
    @Autowired private val clienteBusiness: ClienteBusiness
) {

    fun create(clienteDTO: ClienteDTO){
        clienteBusiness.create(clienteDTO)
    }

}