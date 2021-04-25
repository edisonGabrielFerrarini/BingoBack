package br.com.devtec.bingo.dominio.cliente.dto.converter

import br.com.devtec.bingo.dominio.cliente.dto.ClienteDTO
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente


fun Cliente.toDTO() = ClienteDTO (
    id = id,
    cpf = cpf,
    nome = nome,
    cidade = cidade,
    estado = estado,
    email = email,
    senha = senha,
    celular = celular,
    telefone = telefone,
    ganhos = ganhos,
    saldo = saldo,
)

fun ClienteDTO.toEntity(saldo: Double) = Cliente(
    id = id,
    cpf = cpf,
    nome = nome,
    cidade = cidade,
    estado = estado,
    email = email,
    senha = senha,
    celular = celular,
    telefone = telefone,
    ganhos = ganhos,
    saldo = saldo,
)