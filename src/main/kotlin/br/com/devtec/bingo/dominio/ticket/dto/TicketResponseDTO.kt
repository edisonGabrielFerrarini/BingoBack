package br.com.devtec.bingo.dominio.ticket.dto

import java.time.LocalDateTime

data class TicketResponseDTO(
    val id_cliente: Long,
    val numeros: String,
    val id_cartela: Long,
    val valor: Double,
    val data_compra: LocalDateTime
)