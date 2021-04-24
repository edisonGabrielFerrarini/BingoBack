package br.com.devtec.bingo.dominio.cliente.model.repository

import br.com.devtec.bingo.dominio.cliente.model.entity.Agente
import org.springframework.data.jpa.repository.JpaRepository

interface AgenteRepository: JpaRepository<Agente, Long> {
    fun findByCpf(cpf: String) : Agente
}