package br.com.devtec.bingo.dominio.cliente.business

import br.com.devtec.bingo.dominio.cliente.dto.ClienteGanhosDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteSaldoDTO
import br.com.devtec.bingo.dominio.cliente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import br.com.devtec.bingo.dominio.cliente.model.repository.ClienteRepository
import br.com.devtec.bingo.dominio.cliente.utils.EnumCliente
import br.com.devtec.bingo.dominio.users.model.entity.Users
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ClienteBusiness {

    @Autowired
    lateinit var clienteRepository: ClienteRepository

    fun create(clienteRequestDTO: ClienteRequestDTO, users: Users): ResponseEntity<ClienteResponseDTO> {
        return salvarDados(
            Cliente(
                cpf = clienteRequestDTO.cpf,
                nome = clienteRequestDTO.nome,
                cidade = clienteRequestDTO.cidade,
                estado = clienteRequestDTO.estado,
                celular = clienteRequestDTO.celular,
                telefone = clienteRequestDTO.telefone,
                ganhos = 0.0,
                saldo = 0.0,
                users = users
            )
        )
    }

    fun getAll(pageable: Pageable): Page<ClienteResponseDTO> {
        try {
            val clientes = clienteRepository.findAll(pageable).map {
                it.toResponseDTO()
            }
            return clientes
        } catch (e: Exception) {
            throw PersistirDadosException("erro ao ler dados")
        }
    }


    fun getByCpf(cpf: String): Any? {
        clienteRepository.findByCpf(cpf)?.let {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toResponseDTO())
        }
        return ResponseEntity.status(400).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
    }

    fun update(id: Long, clienteRequestDTO: ClienteRequestDTO): ResponseEntity<ClienteResponseDTO> {
        try {
            val cliente = clienteRepository.findById(id).get()
            return salvarDados(
                cliente.copy(
                    nome = clienteRequestDTO.nome,
                    telefone = clienteRequestDTO.telefone,
                    celular = clienteRequestDTO.celular,
                    cpf = clienteRequestDTO.cpf,
                    ganhos = clienteRequestDTO.ganhos,
                    saldo = clienteRequestDTO.saldo,
                    cidade = clienteRequestDTO.cidade,
                    estado = clienteRequestDTO.estado
                )
            )
        } catch (e: Exception) {
            throw PersistirDadosException("erro ao persistir os dados no banco de dados")
        }
    }

    fun updateSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<ClienteResponseDTO> {
        try {
            clienteRepository.findById(id).get().let {
                return salvarDados(
                    it.copy(
                        saldo = it.saldo + clienteSaldoDTO.saldo
                    )
                )
            }
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCliente.ERRO_AO_PERSISTIR_DADOS.erro)
        }
    }

    fun updateGanhos(id: Long, clienteGanhosDTO: ClienteGanhosDTO): ResponseEntity<ClienteResponseDTO> {
        try {
            clienteRepository.findById(id).get().let {
                println(it.ganhos)
                return salvarDados(
                    it.copy(
                        ganhos = it.ganhos + clienteGanhosDTO.ganhos
                    )
                )
            }
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCliente.ERRO_AO_PERSISTIR_DADOS.erro)
        }
    }

    fun debitarSaldo(id: Long, clienteSaldoDTO: ClienteSaldoDTO): ResponseEntity<Any> {
        try {
            val cliente = clienteRepository.findById(id).get()
            return if (verificarSaldo(cliente.saldo, clienteSaldoDTO.saldo)) {
                val save = salvarDados(
                    cliente.copy(
                        saldo = cliente.saldo - clienteSaldoDTO.saldo
                    )
                )
                ResponseEntity.accepted().body(save)
            } else {
                ResponseEntity.status(400).body("saldo insuficiente")
            }
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
        }
    }

    fun debitarGanhos(id: Long, clienteGanhosDTO: ClienteGanhosDTO): ResponseEntity<ClienteResponseDTO> {
        try {
            val cliente = getByID(id).body as Cliente
            return salvarDados(
                cliente.copy(
                    ganhos = cliente.ganhos - clienteGanhosDTO.ganhos
                )
            )
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCliente.ERRO_AO_PERSISTIR_DADOS.erro)
        }
    }

    fun salvarDados(cliente: Cliente): ResponseEntity<ClienteResponseDTO> {
        try {
            clienteRepository.save(cliente).let {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(it.toResponseDTO())
            }
        } catch (e: Exception) {
            throw PersistirDadosException(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
        }
    }

    fun getByID(id: Long): ResponseEntity<Any> {
        return try {
            val cliente = clienteRepository.findById(id).get()
            ResponseEntity.status(HttpStatus.ACCEPTED).body(cliente)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EnumCliente.CLIENTE_NAO_ENCONTRADO.erro)
        }
    }

    fun verificarSaldo(saldo: Double, saldoDebitar: Double): Boolean {
        val saldoAtualizado = saldo - saldoDebitar
        return saldoAtualizado >= 0
    }

}