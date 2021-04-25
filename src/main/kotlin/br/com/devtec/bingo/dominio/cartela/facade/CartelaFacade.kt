package br.com.devtec.bingo.dominio.cartela.facade

import br.com.devtec.bingo.dominio.cartela.business.CartelaBusiness
import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CartelaFacade {

    @Autowired
    lateinit var cartelaBusiness: CartelaBusiness

    fun create(cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        return cartelaBusiness.create(cartelaDTO)
    }

    fun getAtiva(): ResponseEntity<Any> {
        return cartelaBusiness.get()
    }

    fun inativarCartela(): ResponseEntity<Any> {
        return cartelaBusiness.inativarCartela()
    }

    fun sortear(){
        return cartelaBusiness.sortearNumeros()
    }

    fun getByAtiva(): Cartela? {
        return cartelaBusiness.cartelaAtiva()
    }

}