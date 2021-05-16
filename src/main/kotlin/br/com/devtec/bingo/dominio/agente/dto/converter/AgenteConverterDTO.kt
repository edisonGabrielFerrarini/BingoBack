package br.com.devtec.bingo.dominio.agente.dto.converter

import br.com.devtec.bingo.dominio.agente.dto.AgenteDTO
import br.com.devtec.bingo.dominio.agente.model.entity.Agente
import br.com.devtec.bingo.dominio.gerente.model.entity.Gerente


fun AgenteDTO.toEntity(gerente: Gerente) = Agente(
    nome = nome,
    gerente = gerente,
    celular = celular,
    telefone = telefone,
    cpf = cpf,
    cidade = cidade,
    estado = estado,
    porcentual_venda = porcentual_venda
)

fun Agente.toDTO() = AgenteDTO(
    nome = nome,
    celular = celular,
    telefone = telefone,
    cpf = cpf,
    cidade = cidade,
    estado = estado,
    porcentual_venda = porcentual_venda,
    id_gerente = gerente.id
)