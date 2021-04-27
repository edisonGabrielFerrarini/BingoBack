package br.com.devtec.bingo.dominio.cartela.business

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cartela.model.repository.CartelaRepository
import br.com.devtec.bingo.dominio.cartela.utils.EnumCartela
import br.com.devtec.bingo.dominio.cartela.utils.GeradorNumeros
import br.com.devtec.bingo.dominio.cliente.exception.PersistirDadosException
import br.com.devtec.bingo.dominio.ganhador.facade.GanhadorFacade
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

    @Autowired
    lateinit var ganhadorFacade: GanhadorFacade


    fun create(cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        if (nonNull(cartelaRepository.findByAtiva(ativa = true))) {
            return ResponseEntity.status(400).body(EnumCartela.CartelaAtivaExite.value)
        }
        val save = cartelaRepository.save(cartelaDTO.toEntity())
        if (nonNull(save)) {
            return ResponseEntity.status(400).body(save.toDTO())
        }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun get(): ResponseEntity<Any> {
        cartelaAtiva()?.let {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toDTO())
        }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun inativarCartela(): ResponseEntity<Any> {
        try {
            cartelaAtiva()?.let {
                cartelaRepository.save(
                    it.copy(
                        ativa = false
                    )
                )
            }?.let {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toDTO())
            }
            return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCartela.ErroBanco.value)
        }
    }


    fun gerarSorteio(): ResponseEntity<Any> {
        try {
//            val numeros = geradorNumeros.gerarNumeros().toString()
            val numeros = "[4, 5, 8, 12, 14, 16, 20, 22, 29, 30, 31, 34, 36, 40, 46, 48, 52, 54, 56, 58]"
            cartelaAtiva()?.let {
                cartelaRepository.save(
                    it.copy(
                        numeros_sorteados = numeros
                    )
                )
            }?.run {
                val ganhador = this.numeros_sorteados?.let { ganhadorFacade.create(it) }
                return if (ganhador != null && ganhador.isNotEmpty()) {
                    ResponseEntity.status(HttpStatus.ACCEPTED).body(ganhador)
                } else {
                    ResponseEntity.status(400).body("não houve ganhador")
                }
            }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }catch (e: Exception)
    {
        throw PersistirDadosException(EnumCartela.ErroBanco.value)
    }
}

fun cartelaAtiva(): Cartela? {
    return cartelaRepository.findByAtiva(ativa = true)
}


}

























