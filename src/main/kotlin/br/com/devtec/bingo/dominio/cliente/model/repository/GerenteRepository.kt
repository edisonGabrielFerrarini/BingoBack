package br.com.devtec.bingo.dominio.cliente.model.repository

import br.com.devtec.bingo.dominio.cliente.model.entity.Gerente
import org.springframework.data.jpa.repository.JpaRepository

interface GerenteRepository: JpaRepository<Gerente, Long> {
    fun findByCpf(cpf: String) : Gerente
}