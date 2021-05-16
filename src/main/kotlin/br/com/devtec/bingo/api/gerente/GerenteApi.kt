package br.com.devtec.bingo.api.gerente

import br.com.devtec.bingo.dominio.gerente.dto.GerenteDTO
import br.com.devtec.bingo.dominio.gerente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.gerente.facade.GerenteFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/gerente"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GerenteApi(
    @Autowired private val gerenteFacade: GerenteFacade
) {

    @PostMapping
    fun create(@RequestBody gerenteDTO: GerenteDTO): GerenteDTO {
        return gerenteFacade.create(gerenteDTO)
    }

    @GetMapping
    fun getAll(pageable: Pageable): Page<GerenteDTO> {
        return gerenteFacade.getAll(pageable)
    }

    @GetMapping(value = ["/id/{id}"])
    fun getById(@PathVariable("id") id: Long): GerenteDTO {
        return gerenteFacade.getById(id).get().toDTO()
    }

    @GetMapping(value = ["/cpf/{cpf}"])
    fun getByCpf(@PathVariable("cpf") cpf: String): ResponseEntity<GerenteDTO> {
        return gerenteFacade.getByCPF(cpf)
    }
}