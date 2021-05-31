package br.com.devtec.bingo.dominio.cartela.facade

import br.com.devtec.bingo.dominio.cartela.business.CartelaBusiness
import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.CartelaNumerosDTO
import br.com.devtec.bingo.dominio.cartela.dto.CartelaRendimentosDTO
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    fun getAll(pageable: Pageable): Page<Cartela> {
        return cartelaBusiness.getAll(pageable)
    }

    fun inativarCartela(): ResponseEntity<Any> {
        return cartelaBusiness.cancelar()
    }

    fun sortear(cartelaNumerosDTO: CartelaNumerosDTO): ResponseEntity<Any> {
        return cartelaBusiness.gerarSorteio(cartelaNumerosDTO)
    }

    fun getByAtiva(): Cartela? {
        return cartelaBusiness.cartelaAtiva()
    }

    fun obeterRendimentos(): CartelaRendimentosDTO {
        return cartelaBusiness.buscarCartelaERendimentos()
    }

    fun obterUltimosSorteios(): List<Cartela> {
        return cartelaBusiness.obterUltimosSorteios()
    }

}