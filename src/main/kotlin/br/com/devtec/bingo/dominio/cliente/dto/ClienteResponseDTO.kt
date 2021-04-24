package br.com.devtec.bingo.dominio.cliente.dto

data class ClienteResponseDTO(
    val id: Long,
    val nome: String,
    val telefone: String,
    val celular: String,
    val cpf: String,
    val ganhos: Double,
    val saldo: Double,
    val cidade: String,
    val estado: String
)
