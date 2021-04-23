package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Cliente
import br.com.devtec.bingo.dominio.bingo.model.entity.Gerente
import org.springframework.data.jpa.repository.JpaRepository

interface GerenteRepository: JpaRepository<Gerente, Long> {
    fun findByCpf(cpf: String) : Gerente
}