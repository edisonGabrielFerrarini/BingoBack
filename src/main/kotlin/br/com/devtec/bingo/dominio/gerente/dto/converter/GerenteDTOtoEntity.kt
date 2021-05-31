package br.com.devtec.bingo.dominio.gerente.dto.converter

import br.com.devtec.bingo.dominio.gerente.dto.GerenteDTO
import br.com.devtec.bingo.dominio.gerente.dto.GerenteResponseDTO
import br.com.devtec.bingo.dominio.gerente.model.entity.Gerente

fun GerenteDTO.toEntiy() = Gerente(
    nome = nome,
    estado = estado,
    cidade = cidade,
    cpf = cpf,
    celular = celular,
    telefone = telefone
)

fun Gerente.toDTO() = GerenteDTO(
    nome = nome,
    estado = estado,
    cidade = cidade,
    cpf = cpf,
    celular = celular,
    telefone = telefone
)

fun Gerente.toResponseDTO() = GerenteResponseDTO(
    id = id,
    nome = nome,
    estado = estado,
    cidade = cidade,
    cpf = cpf,
    celular = celular,
    telefone = telefone
)