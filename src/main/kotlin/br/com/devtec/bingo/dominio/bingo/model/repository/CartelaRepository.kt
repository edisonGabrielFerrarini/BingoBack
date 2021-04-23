package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Cartela
import org.springframework.data.jpa.repository.JpaRepository

interface CartelaRepository: JpaRepository<Cartela, Long> {
}