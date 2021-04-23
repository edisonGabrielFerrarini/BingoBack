package br.com.devtec.bingo.dominio.bingo.dto

data class ClienteDTO(
    val nome: String,
    val id_usuario: Long,
    val id_agente: Long,
    val telefone: String,
    val celular: String,
    val cpf: String,
    val ganhos: Double,
    val saldo: Double,
    val cidade: String,
    val bairro: String,
    val cep: String,
    val estado: String
)
