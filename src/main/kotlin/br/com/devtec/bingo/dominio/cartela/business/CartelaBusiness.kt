package br.com.devtec.bingo.dominio.cartela.business

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.dto.CartelaNumerosDTO
import br.com.devtec.bingo.dominio.cartela.dto.CartelaRendimentosDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toAcumuladoDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cartela.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cartela.model.repository.CartelaRepository
import br.com.devtec.bingo.dominio.cartela.utils.EnumCartela
import br.com.devtec.bingo.dominio.cartela.utils.GeradorNumeros
import br.com.devtec.bingo.dominio.ganhador.dto.converter.toDTO
import br.com.devtec.bingo.dominio.ganhador.facade.GanhadorFacade
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador
import br.com.devtec.bingo.dominio.ticket.facade.TicketFacade
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Objects.nonNull
import kotlin.math.ceil

@Service
class CartelaBusiness {

    @Autowired
    lateinit var cartelaRepository: CartelaRepository

    @Autowired
    lateinit var geradorNumeros: GeradorNumeros

    @Autowired
    lateinit var ganhadorFacade: GanhadorFacade

    @Autowired
    lateinit var ticketFacade: TicketFacade


    fun create(cartelaDTO: CartelaDTO): ResponseEntity<Any> {
        if (cartelaRepository.findByAtiva(ativa = true).isNotEmpty()) {
            return ResponseEntity.badRequest().body(EnumCartela.CartelaAtivaExite.value)
        }
        val acumulada = getByAcumulada()
        if (acumulada != null) {
            val save = cartelaRepository.save(
                acumulada.copy(
                    numeros_sorteados = cartelaDTO.numeros_sorteados,
                    ativa = cartelaDTO.ativa,
                    acumulada = false,
                    valor_numero = cartelaDTO.valor_numero,
                    valor_porcentagem = cartelaDTO.valor_porcentagem,
                    valor = acumulada.valor_acumulado + cartelaDTO.valor
                )
            )
            if (nonNull(save)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(save.toDTO())
            }
        }

        val save = cartelaRepository.save(cartelaDTO.toEntity())
        if (nonNull(save)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(save.toDTO())
        }

        return ResponseEntity.status(400).body(EnumCartela.InicieUmaCartela.value)
    }

    fun getByAcumulada(): Cartela? {
        return cartelaRepository.findByAcumulada(true)
    }

    fun get(): ResponseEntity<Any> {
        cartelaAtiva().let {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toDTO())
        }
    }

    fun cancelar(acumulada: Boolean = false): ResponseEntity<Any> {
        try {
            cartelaAtiva().let {
                cartelaRepository.delete(
                    it
                )
            }.let {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("removido com sucesso")
            }
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCartela.ErroBanco.value)
        }
    }

    fun inativarCartela(acumulada: Boolean = false): ResponseEntity<Any> {
        try {
            cartelaAtiva().let {
                cartelaRepository.save(
                    it.copy(
                        ativa = false,
                        acumulada = acumulada
                    )
                )
            }.let {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toDTO())
            }
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCartela.ErroBanco.value)
        }
    }

    fun gerarSorteio(cartelaNumerosDTO: CartelaNumerosDTO): ResponseEntity<Any> {
        try {
            cartelaAtiva().let {
                salvarNumerosSoretados(it, cartelaNumerosDTO.numeros)
            }.run {

                val ganhador = numeros_sorteados?.let { gerarGanhador(it, this) } ?: listOf()
                return if (ganhador.isNotEmpty()) {
                    inativarCartela()
                    val ganhadorDTO = ganhador.map {
                        it.toDTO()
                    }
                    ResponseEntity.status(HttpStatus.ACCEPTED).body(ganhadorDTO)
                } else {
                    inativarCartela()
                    criarTabelaAcumulada(this.toAcumuladoDTO())
                    ResponseEntity.status(200).body("Não houve ganhadores, sua cartela foi acumulada")
                }
            }

        } catch (e: Exception) {
            throw PersistirDadosException(EnumCartela.ErroBanco.value)
        }
    }

    fun criarTabelaAcumulada(cartelaDTO: CartelaDTO) {
        val valor_acumulado = ceil((cartelaDTO.valor * cartelaDTO.valor_porcentagem) / 100)
        cartelaRepository.save(
            cartelaDTO.toEntity().copy(
                acumulada = true,
                ativa = true,
                valor = cartelaDTO.valor + valor_acumulado,
                valor_numero = cartelaDTO.valor_numero,
                valor_acumulado = valor_acumulado,
                numeros_sorteados = ""
            )
        )
    }

    fun gerarGanhador(numerosSorteados: String, cartela: Cartela): List<Ganhador>? {
        val numeros = numerosSorteados
            .replace("[","%")
            .replace("]","%")
            .replace(",","%")
        return ganhadorFacade.create(numeros, cartela)
    }

    fun salvarNumerosSoretados(cartela: Cartela, numerosSorteados: String): Cartela {
        return cartelaRepository.save(
            cartela.copy(
                numeros_sorteados = numerosSorteados
            )
        )
    }

    fun cartelaAtiva(): Cartela {
        try {
            return cartelaRepository.findByAtiva(ativa = true)[0]
        } catch (e: Exception) {
            throw PersistirDadosException("Nenhuma cartela ativa")
        }
    }

    fun buscarCartelaERendimentos(): CartelaRendimentosDTO {
        val cartela = cartelaAtiva()
        val rendimentos = ticketFacade.obeterRendimentos(cartela.valor_numero.toInt())
        return CartelaRendimentosDTO(
            id = cartela.id,
            valor = cartela.valor,
            rendimentos = rendimentos.toDouble(),
            valor_numero = cartela.valor_numero
        )
    }

    fun getAll(pageable: Pageable): Page<Cartela> {
        try {
            return cartelaRepository.findByAtiva(false, pageable)
        } catch (e: Exception) {
            throw PersistirDadosException("Cartela não encontrada")
        }
    }

    fun obterUltimosSorteios(): List<Cartela> {
        try {
            val cartela = mutableListOf<Cartela>()
            cartelaRepository
                .findByAtiva(false)
                .sortedByDescending { it.id }
                .run {
                    this.forEach {
                        if (cartela.size <= 4) {
                            cartela.add(it)
                        }
                    }
                }

            return cartela
        } catch (e: Exception) {
            throw PersistirDadosException("Cartela não encontrada")
        }
    }
}