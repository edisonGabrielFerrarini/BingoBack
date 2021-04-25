package br.com.devtec.bingo.api.cartela

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.facade.CartelaFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["api/cartela"], produces = [MediaType.APPLICATION_JSON_VALUE])
class CartelaApi(
    @Autowired private val cartelaFacade: CartelaFacade
) {

    @PostMapping(value = ["/create"])
    fun create(@RequestBody cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        return cartelaFacade.create(cartelaDTO)
    }

    @GetMapping(value = ["/all"])
    fun get(): ResponseEntity<Any> {
        return cartelaFacade.getAtiva()
    }

    @GetMapping(value = ["/inativar"])
    fun inativarCartela(): ResponseEntity<Any> {
        return cartelaFacade.inativarCartela()
    }

    @GetMapping(value = ["/sortear"])
    fun sortearNumeros(){
        return cartelaFacade.sortear()
    }

}