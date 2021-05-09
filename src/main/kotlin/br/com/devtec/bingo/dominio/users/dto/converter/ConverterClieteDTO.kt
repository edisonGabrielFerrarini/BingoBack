package br.com.devtec.bingo.dominio.users.dto.converter

import br.com.devtec.bingo.dominio.users.dto.UsersAdminDTO
import br.com.devtec.bingo.dominio.users.dto.UsersDTO
import br.com.devtec.bingo.dominio.users.dto.UsersLoginDTO
import br.com.devtec.bingo.dominio.users.model.entity.Roles
import br.com.devtec.bingo.dominio.users.model.entity.Users

fun UsersDTO.toEntity(senhaCrip: String) = Users(
    email = email,
    senha = senhaCrip,
    roles = mutableListOf(Roles("ROLE_USER"))
)

fun UsersAdminDTO.toEntity(senhaCrip: String) = Users(
    email = email,
    senha = senhaCrip,
    roles = mutableListOf(Roles("ROLE_ADMIN"))
)