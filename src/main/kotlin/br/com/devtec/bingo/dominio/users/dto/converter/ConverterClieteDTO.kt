package br.com.devtec.bingo.dominio.users.dto.converter

import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.model.entity.Users

fun UsersDTO.toEntity() = Users(
    email = email,
    password = password
)