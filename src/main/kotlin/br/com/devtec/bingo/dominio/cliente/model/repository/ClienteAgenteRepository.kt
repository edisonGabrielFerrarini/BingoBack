package br.com.devtec.bingo.dominio.cliente.model.repository

import br.com.devtec.bingo.dominio.cliente.model.entity.ClienteAgente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteAgenteRepository: JpaRepository<ClienteAgente, Long> {
}