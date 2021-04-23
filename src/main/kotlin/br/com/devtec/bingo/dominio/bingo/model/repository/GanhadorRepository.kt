package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Cliente
import br.com.devtec.bingo.dominio.bingo.model.entity.Ganhador
import org.springframework.data.jpa.repository.JpaRepository

interface GanhadorRepository: JpaRepository<Ganhador, Long> {
}