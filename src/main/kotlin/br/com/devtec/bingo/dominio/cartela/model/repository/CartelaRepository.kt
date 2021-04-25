package br.com.devtec.bingo.dominio.cartela.model.repository

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import org.springframework.data.jpa.repository.JpaRepository

interface CartelaRepository: JpaRepository<Cartela, Long> {
    fun findByAtiva(ativa: Boolean) : Cartela?
}