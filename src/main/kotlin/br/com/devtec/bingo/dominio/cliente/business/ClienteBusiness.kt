package br.com.devtec.bingo.dominio.cliente.business

import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.cliente.dto.converter.toEntity
import br.com.devtec.bingo.dominio.cliente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.cliente.exception.PersistirDadosException
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import br.com.devtec.bingo.dominio.cliente.model.repository.ClienteRepository
import br.com.devtec.bingo.dominio.cliente.utils.EnumCliente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Objects.nonNull

@Service
class ClienteBusiness {

    @Autowired
    lateinit var clienteRepository: ClienteRepository

    fun create(clienteRequestDTO: ClienteRequestDTO): Any {
        clienteRepository.findByCpf(clienteRequestDTO.cpf)?.run {
            return ResponseEntity.status(400).body("Não foi possivel cadastrar este cliente, pois já esta cadastrado.")
        }
        return salvarDados(clienteRequestDTO.toEntity(saldo = 0.0, ganhos = 0.0))
    }

    fun getAll(): Any {
        val allClientes = clienteRepository.findAll().asSequence().map {
            it.toResponseDTO()
        }.toList()

        if (allClientes.isNotEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(allClientes)
        }

        return ResponseEntity.status(400).body("não foi encontrado nenhum cliente")
    }


    fun getByCpf(cpf: String): Any? {
        clienteRepository.findByCpf(cpf)?.let {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toResponseDTO())
        }
        return ResponseEntity.status(400).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
    }

    fun update(id: Long, clienteRequestDTO: ClienteRequestDTO): ResponseEntity<Any> {
        if (clienteRepository.existsById(id)) {
            return salvarDados(clienteRequestDTO.toEntity(id))
        }
        return ResponseEntity.status(400).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
    }

    fun updateSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        try {
            clienteRepository.findById(id).get().let {
                return salvarDados(
                    it.copy(
                        saldo = it.saldo + clienteSaldoDTO.saldo
                    )
                )
            }
        } catch (e: Exception){
            throw PersistirDadosException(EnumCliente.ERRO_AO_PERSISTIR_DADOS.erro)
        }
    }

    fun updateGanhos(id: Long, clienteGanhosDTO: ClienteGanhosDTO): ResponseEntity<Any> {
        try {
            clienteRepository.findById(id).get().let {
                return salvarDados(
                    it.copy(
                        ganhos = it.ganhos + clienteGanhosDTO.ganhos
                    )
                )
            }
        } catch (e: Exception){
            throw PersistirDadosException(EnumCliente.ERRO_AO_PERSISTIR_DADOS.erro)
        }
    }

    fun debitarSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        val cliente = clienteRepository.findById(id).get()
        if (nonNull(cliente)) {
            return if (verificarSaldo(cliente.saldo, clienteSaldoDTO.saldo)) {
                salvarDados(
                    cliente.copy(
                        saldo = cliente.saldo - clienteSaldoDTO.saldo
                    )
                )
            }else {
                ResponseEntity.status(400).body("saldo insuficiente")
            }
        }
        return ResponseEntity.status(400).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
    }

    fun debitarGanhos(id: Long, clienteGanhosDTO: ClienteGanhosDTO): ResponseEntity<Any> {
        val cliente = getByID(id).body as Cliente
        if (nonNull(cliente)) {
            return salvarDados(
                cliente.copy(
                    ganhos = cliente.ganhos - clienteGanhosDTO.ganhos
                )
            )
        }
        return ResponseEntity.status(400).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
    }

    fun salvarDados(cliente: Cliente): ResponseEntity<Any> {
        try {
            clienteRepository.save(cliente).let {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toResponseDTO())
            }
        }catch (e: Exception){
            throw e.message?.let { PersistirDadosException(it) }!!
        }
    }


    fun getByID(id: Long): ResponseEntity<Any> {
        try {
            val cliente = clienteRepository.findById(id).get()
            if (nonNull(cliente)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(cliente)
            }
        }catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel encontrar este cliente")
    }


    fun verificarSaldo(saldo: Double, saldoDebitar: Double): Boolean {
        val saldoAtualizado = saldo - saldoDebitar
        return saldoAtualizado >= 0
    }

}