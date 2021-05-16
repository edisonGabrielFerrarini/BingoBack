package br.com.devtec.bingo.dominio.ganhador.dto.converter

import br.com.devtec.bingo.dominio.ganhador.dto.GanhadorDTO
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador

fun Ganhador.toDTO() = GanhadorDTO(
    id = id,
    nome = ticket.cliente.nome,
    id_cartela = ticket.cartela.id,
    id_ticket = ticket.id,
    id_cliente = ticket.cliente.id,
    estado = ticket.cliente.estado,
    valor_premio = ticket.cartela.valor,
    numeros = ticket.cartela.numeros_sorteados,
    data_premio = ticket.created_at
)