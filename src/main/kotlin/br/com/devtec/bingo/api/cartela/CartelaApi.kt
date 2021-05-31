package br.com.devtec.bingo.api.cartela

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.CartelaNumerosDTO
import br.com.devtec.bingo.dominio.cartela.dto.CartelaRendimentosDTO
import br.com.devtec.bingo.dominio.cartela.facade.CartelaFacade
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
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

    @GetMapping(value = ["/all"])
    fun getAll(pageable: Pageable): Page<Cartela> {
        return cartelaFacade.getAll(pageable)
    }

    @GetMapping(value = ["/ultimo"])
    fun obterUltimosSorteios(): List<Cartela> {
        return cartelaFacade.obterUltimosSorteios()
    }

    @PostMapping(value = ["/sorteia"])
    fun sortearNumeros(
        @RequestBody cartelaNumerosDTO: CartelaNumerosDTO
    ): ResponseEntity<Any> {
        return cartelaFacade.sortear(cartelaNumerosDTO)
    }

    @GetMapping(value = ["/rendimento"])
    fun rendimentos(): CartelaRendimentosDTO {
        return cartelaFacade.obeterRendimentos()
    }

    @GetMapping(value = ["/cancela"])
    fun cancelar(): ResponseEntity<Any> {
        return cartelaFacade.inativarCartela()
    }

}