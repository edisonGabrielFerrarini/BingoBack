package br.com.devtec.bingo.dominio.ganhador.model.repository

import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador
import org.springframework.data.jpa.repository.JpaRepository

interface GanhadorRepository: JpaRepository<Ganhador, Long> {
}