package br.com.devtec.bingo.dominio.cliente.model.repository

import br.com.devtec.bingo.dominio.cliente.model.entity.Ganhador
import org.springframework.data.jpa.repository.JpaRepository

interface GanhadorRepository: JpaRepository<Ganhador, Long> {
}