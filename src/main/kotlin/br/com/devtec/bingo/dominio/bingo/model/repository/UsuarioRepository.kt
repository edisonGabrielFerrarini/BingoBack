package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
}