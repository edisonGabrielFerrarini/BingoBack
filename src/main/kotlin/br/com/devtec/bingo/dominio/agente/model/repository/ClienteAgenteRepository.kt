package br.com.devtec.bingo.dominio.agente.model.repository

import br.com.devtec.bingo.dominio.agente.model.entity.ClienteAgente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteAgenteRepository: JpaRepository<ClienteAgente, Long> {
}