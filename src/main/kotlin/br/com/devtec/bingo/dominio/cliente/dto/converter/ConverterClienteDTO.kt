package br.com.devtec.bingo.dominio.cliente.dto.converter

import br.com.devtec.bingo.dominio.cliente.dto.ClienteDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteRequestDTO
import br.com.devtec.bingo.dominio.cliente.dto.ClienteResponseDTO
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente


fun ClienteRequestDTO.toEntity(id: Long) = Cliente(
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

fun ClienteRequestDTO.toEntity() = Cliente(
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


fun ClienteRequestDTO.toEntity(saldo: Double, ganhos: Double) = Cliente(
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