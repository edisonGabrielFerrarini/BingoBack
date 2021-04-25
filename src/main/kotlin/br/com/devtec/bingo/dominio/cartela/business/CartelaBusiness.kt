package br.com.devtec.bingo.dominio.cartela.business

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cartela.dto.converter.toInativeEntity
import br.com.devtec.bingo.dominio.cartela.model.repository.CartelaRepository
import br.com.devtec.bingo.dominio.cartela.utils.EnumCartela
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Objects.nonNull

@Service
class CartelaBusiness {

    @Autowired
    lateinit var cartelaRepository: CartelaRepository

    fun create(cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        if (nonNull(cartelaRepository.findByAtiva(true))){
            return ResponseEntity.status(400).body(EnumCartela.CartelaAtivaExite.value)
        }
        val save = cartelaRepository.save(cartelaDTO.toEntity())
        if (nonNull(save)){
            return ResponseEntity.status(400).body(save.toDTO())
        }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun get(): ResponseEntity<Any> {
        val cartela = cartelaRepository.findByAtiva(true)
        return if (nonNull(cartela)){
            ResponseEntity.status(HttpStatus.ACCEPTED).body(cartela?.toDTO())
        }else {
            ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
        }
    }

    fun inativarCartela(): ResponseEntity<Any> {
        val cartela = cartelaRepository.findByAtiva(true)
        if (nonNull(cartela)){
            val save = cartela?.toDTO()?.let { cartelaRepository.save(it.toInativeEntity(cartela.id)) }
            return if (nonNull(save)){
                ResponseEntity.status(HttpStatus.ACCEPTED).body(save)
            }else {
                ResponseEntity.status(400).body(EnumCartela.ErroBanco.value)
            }
        }

        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }


}