package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Agente
import org.springframework.data.jpa.repository.JpaRepository

interface AgenteRepository: JpaRepository<Agente, Long> {
    fun findByCpf(cpf: String) : Agente
}