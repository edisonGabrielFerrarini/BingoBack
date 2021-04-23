package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Cliente
import br.com.devtec.bingo.dominio.bingo.model.entity.Contato
import org.springframework.data.jpa.repository.JpaRepository

interface ContatoRepository: JpaRepository<Contato, Long> {
    fun findByCpf(cpf: String) : Contato
}