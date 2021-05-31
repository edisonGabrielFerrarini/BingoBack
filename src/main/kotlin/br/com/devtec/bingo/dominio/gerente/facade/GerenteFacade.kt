package br.com.devtec.bingo.dominio.gerente.facade

import br.com.devtec.bingo.dominio.gerente.business.GerenteBusiness
import br.com.devtec.bingo.dominio.gerente.dto.GerenteDTO
import br.com.devtec.bingo.dominio.gerente.dto.GerenteResponseDTO
import br.com.devtec.bingo.dominio.gerente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.gerente.model.entity.Gerente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class GerenteFacade(
    @Autowired private val gerenteBusiness: GerenteBusiness
) {

    fun create(gerenteDTO: GerenteDTO): GerenteDTO {
        return gerenteBusiness.create(gerenteDTO).toDTO()
    }

    fun getByCPF(cpf: String): ResponseEntity<GerenteDTO> {
        return gerenteBusiness.getByCPF(cpf)
    }

    fun getAll(pageable: Pageable): Page<GerenteResponseDTO> {
        return gerenteBusiness.getAll(pageable)
    }

    fun getById(id: Long): Optional<Gerente> {
        return gerenteBusiness.getById(id)
    }


}