package br.com.devtec.bingo.dominio.cartela.model.repository

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CartelaRepository: JpaRepository<Cartela, Long> {
    fun findByAtiva(ativa: Boolean) : Cartela?

    fun findByAcumulada(acumulada: Boolean): Cartela
}