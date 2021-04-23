package br.com.devtec.bingo.dominio.bingo.dto.converter

import br.com.devtec.bingo.dominio.bingo.dto.ClienteDTO
import br.com.devtec.bingo.dominio.bingo.model.entity.Agente
import br.com.devtec.bingo.dominio.bingo.model.entity.Cliente
import br.com.devtec.bingo.dominio.bingo.model.entity.Usuario
import java.util.*

fun ClienteDTO.toEntity(agente: Optional<Agente>, usuario: Optional<Usuario>) = Cliente(
    agente = agente.get(),
    cpf = cpf,
    nome = nome,
    cep = cep,
    cidade = cidade,
    estado = estado,
    celular = celular,
    telefone = telefone,
    bairro = bairro,
    ganhos = ganhos,
    saldo = saldo,
    usuario = usuario.get()
)