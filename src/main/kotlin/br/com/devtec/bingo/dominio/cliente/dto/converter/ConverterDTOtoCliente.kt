package br.com.devtec.bingo.dominio.cliente.dto.converter

import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente

fun Cliente.toResponseDTO() = ClienteResponseDTO(
    id = id,
    cpf = cpf,
    nome = nome,
    cidade = cidade,
    estado = estado,
    celular = celular,
    telefone = telefone,
    ganhos = ganhos,
    saldo = saldo,
)
