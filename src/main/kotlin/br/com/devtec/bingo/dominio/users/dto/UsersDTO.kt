package br.com.devtec.bingo.dominio.users.dto

import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO

data class UsersDTO(
    val email: String,
    val password: String,
    val cliente: ClienteRequestDTO
)