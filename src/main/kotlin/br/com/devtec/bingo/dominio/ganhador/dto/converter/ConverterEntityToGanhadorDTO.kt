package br.com.devtec.bingo.dominio.ganhador.dto.converter

import br.com.devtec.bingo.dominio.ganhador.dto.GanhadorDTO
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador

fun Ganhador.toDTO() = GanhadorDTO(
    nome = ticket.cliente.nome,
    id_cartela = ticket.cartela.id,
    id_ticket = ticket.id,
    valor_premio = ticket.cartela.valor,
    numeros = ticket.cartela.numeros_sorteados,
    data_premio = ticket.created_at
)