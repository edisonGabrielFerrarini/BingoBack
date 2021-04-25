package br.com.devtec.bingo.dominio.cartela.business

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cartela.model.repository.CartelaRepository
import br.com.devtec.bingo.dominio.cartela.utils.EnumCartela
import br.com.devtec.bingo.dominio.cartela.utils.GeradorNumeros
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Objects.nonNull

@Service
class CartelaBusiness {

    @Autowired
    lateinit var cartelaRepository: CartelaRepository

    @Autowired
    lateinit var geradorNumeros: GeradorNumeros

    fun create(cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        if (nonNull(cartelaRepository.findByAtiva(ativa = true))){
            return ResponseEntity.status(400).body(EnumCartela.CartelaAtivaExite.value)
        }
        val save = cartelaRepository.save(cartelaDTO.toEntity())
        if (nonNull(save)){
            return ResponseEntity.status(400).body(save.toDTO())
        }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun get(): ResponseEntity<Any> {
        val cartela = cartelaAtiva()
        return if (nonNull(cartela)){
            ResponseEntity.status(HttpStatus.ACCEPTED).body(cartela?.toDTO())
        }else {
            ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
        }
    }

    fun inativarCartela(): ResponseEntity<Any> {
        val cartela = cartelaAtiva()
        if (nonNull(cartela)){
            val save = cartela?.let {
                cartelaRepository.save(
                    it.copy(
                        ativa = false
                    ))
            }
            return if (nonNull(save)){
                ResponseEntity.status(HttpStatus.ACCEPTED).body(save?.toDTO())
            }else {
                ResponseEntity.status(400).body(EnumCartela.ErroBanco.value)
            }
        }

        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }


    fun sortearNumeros(){
        var cartela = cartelaAtiva()
        var numeros = geradorNumeros.gerarNumeros().toString()
        if (nonNull(cartela)) {
            var save = cartela?.let {
                cartelaRepository.save(
                    it.copy(
                        numeros_sorteados = numeros
                    ))
            }
            println(save)
        }
    }

    fun cartelaAtiva(): Cartela? {
        return cartelaRepository.findByAtiva(ativa = true)
    }


}

























