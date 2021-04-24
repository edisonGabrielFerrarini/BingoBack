package br.com.devtec.bingo.dominio.cliente.model.repository

import br.com.devtec.bingo.dominio.cliente.model.entity.Cartela
import org.springframework.data.jpa.repository.JpaRepository

interface CartelaRepository: JpaRepository<Cartela, Long> {
}