package br.com.devtec.bingo.dominio.ticket.dto.converter

import br.com.devtec.bingo.dominio.ticket.dto.TicketResponseDTO
import br.com.devtec.bingo.dominio.ticket.model.entity.Ticket

fun Ticket.toDTO() = TicketResponseDTO(
    id_cartela = cartela.id,
    id_cliente = cliente.id,
    valor = valor,
    numeros = numeros,
    data_compra = created_at
)