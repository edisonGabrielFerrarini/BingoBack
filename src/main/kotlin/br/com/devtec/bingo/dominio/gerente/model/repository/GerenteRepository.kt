package br.com.devtec.bingo.dominio.gerente.model.repository

import br.com.devtec.bingo.dominio.gerente.model.entity.Gerente
import org.springframework.data.jpa.repository.JpaRepository

interface GerenteRepository: JpaRepository<Gerente, Long> {
    fun findByCpf(cpf: String) : Gerente
}