package br.com.devtec.bingo.dominio.users.model.repository

import br.com.devtec.bingo.dominio.users.model.entity.Roles
import org.springframework.data.jpa.repository.JpaRepository

interface RolesRepository: JpaRepository<Roles, String> {
}