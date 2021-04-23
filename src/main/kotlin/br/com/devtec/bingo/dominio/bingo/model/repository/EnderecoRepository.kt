package br.com.devtec.bingo.dominio.bingo.model.repository

import br.com.devtec.bingo.dominio.bingo.model.entity.Cliente
import br.com.devtec.bingo.dominio.bingo.model.entity.Endereco
import org.springframework.data.jpa.repository.JpaRepository

interface EnderecoRepository: JpaRepository<Endereco, Long> {
}