package br.com.devtec.bingo.dominio.cliente.model.repository

import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository: JpaRepository<Cliente, Long> {
    fun findByCpf(cpf: String) : Cliente?
    fun findByUsers(users: Users): Cliente
}