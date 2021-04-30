package br.com.devtec.bingo.api.ganhador

import br.com.devtec.bingo.dominio.ganhador.dto.GanhadorDTO
import br.com.devtec.bingo.dominio.ganhador.facade.GanhadorFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/ganhador"], produces = [MediaType.APPLICATION_JSON_VALUE])
class GanhadorApi(
    @Autowired private val ganhadorFacade: GanhadorFacade
) {

    @GetMapping(value = ["/all"])
    fun getAll(pageable: Pageable): ResponseEntity<List<GanhadorDTO>> {
        return ResponseEntity.accepted().body(ganhadorFacade.getAll(pageable))
    }


}