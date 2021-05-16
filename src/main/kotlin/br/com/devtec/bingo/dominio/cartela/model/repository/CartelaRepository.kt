package br.com.devtec.bingo.dominio.cartela.model.repository

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface CartelaRepository: JpaRepository<Cartela, Long> {
    fun findByAtiva(ativa: Boolean) : List<Cartela>


    fun findByAtivaAndAcumulada(ativa: Boolean, acumulada: Boolean) : List<Cartela>

    fun findByAtiva(ativa: Boolean, pageable: Pageable) : Page<Cartela>

    fun findByAcumulada(acumulada: Boolean): Cartela?
}