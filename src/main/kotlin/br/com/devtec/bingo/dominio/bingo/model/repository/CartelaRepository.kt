package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Cartela
import br.com.devtec.bingo.dominio.bingo.model.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface CartelaRepository: JpaRepository<Cartela, Long> {
    fun findByCpf(cpf: String) : Cartela
}