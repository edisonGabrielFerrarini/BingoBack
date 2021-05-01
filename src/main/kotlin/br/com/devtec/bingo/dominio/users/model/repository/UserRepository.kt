package br.com.devtec.bingo.dominio.users.model.repository

import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<Users, Long> {
    fun findByEmail(email: String?): Users?
}