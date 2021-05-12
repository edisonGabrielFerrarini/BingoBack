package br.com.devtec.bingo.dominio.ganhador.dto

import java.time.LocalDateTime

data class GanhadorDTO(
    val id: Long,
    val nome: String,
    val id_ticket: Long,
    val estado: String,
    val id_cartela: Long,
    val valor_premio: Double,
    val numeros: String?,
    val data_premio: LocalDateTime
)


