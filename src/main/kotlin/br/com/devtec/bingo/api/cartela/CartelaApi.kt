package br.com.devtec.bingo.api.cartela

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.facade.CartelaFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://192.168.0.11:8081"])
@RequestMapping(value = ["api/cartela"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CartelaApi(
    @Autowired private val cartelaFacade: CartelaFacade
) {

    @PostMapping
    fun create(@RequestBody cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        return cartelaFacade.create(cartelaDTO)
    }

    @GetMapping
    fun get(pageable: Pageable): ResponseEntity<Any> {
        return cartelaFacade.getAtiva()
    }

    @GetMapping(value = ["/inativa"])
    fun inativarCartela(): ResponseEntity<Any> {
        return cartelaFacade.inativarCartela()
    }

    @GetMapping(value = ["/sorteia"])
    fun sortearNumeros(): ResponseEntity<Any> {
        return cartelaFacade.sortear()
    }

}