package br.com.devtec.bingo.dominio.users.dto

import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO

data class UsersDTO(
    val email: String,
    val senha: String,
    val cliente: ClienteRequestDTO
)

data class UsersLoginDTO(
    val email: String,
)