package br.com.devtec.bingo.dominio.cartela.business

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toAcumuladoDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cartela.model.repository.CartelaRepository
import br.com.devtec.bingo.dominio.cartela.utils.EnumCartela
import br.com.devtec.bingo.dominio.cartela.utils.GeradorNumeros
import br.com.devtec.bingo.dominio.cliente.exception.PersistirDadosException
import br.com.devtec.bingo.dominio.ganhador.facade.GanhadorFacade
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador
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
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save.toDTO())
        }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun get(): ResponseEntity<Any> {
        cartelaAtiva()?.let {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toDTO())
        }
        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun inativarCartela(acumulada: Boolean = false): ResponseEntity<Any> {
        try {
            cartelaAtiva()?.let {
                cartelaRepository.save(
                    it.copy(
                        ativa = false,
                        acumulada = acumulada
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
//            val numeros = geradorNumeros.gerarNumeros()
            val numeros = "[4, 5, 8, 12, 14, 16, 20, 22, 29, 30, 31, 34, 36, 40, 46, 48, 52, 54, 56, 58]"
            cartelaAtiva()?.let {
                salvarNumerosSoretados(it, numeros)
            }?.run {
                val ganhador = gerarGanhador(numeros_sorteados, this)?: listOf()
                return if (ganhador.isNotEmpty()) {
                    inativarCartela()
                    ResponseEntity.status(HttpStatus.ACCEPTED).body(ganhador)
                } else {
                    inativarCartela()
                    criarTabelaAcumulada(this.toAcumuladoDTO())
                    ResponseEntity.status(400).body("Não houve ganhadores, sua cartela foi acumulada")
                }
            }
            return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCartela.ErroBanco.value)
        }
    }

    fun criarTabelaAcumulada(cartelaDTO: CartelaDTO) =
        cartelaRepository.save(
            cartelaDTO.toEntity().copy(
                acumulada = true,
                ativa = false
            )
        )

    fun gerarGanhador(numerosSorteados: String?, cartela: Cartela): List<Ganhador>? {
        return numerosSorteados?.let { ganhadorFacade.create(it, cartela) }
    }

    fun salvarNumerosSoretados(cartela: Cartela, numerosSorteados: String): Cartela {
        return cartelaRepository.save(
            cartela.copy(
                numeros_sorteados = numerosSorteados
            )
        )
    }

    fun cartelaAtiva(): Cartela? {
        return cartelaRepository.findByAtiva(ativa = true)
    }


}

























