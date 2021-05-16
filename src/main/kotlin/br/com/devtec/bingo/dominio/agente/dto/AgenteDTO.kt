package br.com.devtec.bingo.dominio.agente.dto

data class AgenteDTO(
    val nome: String,
    val telefone: String,
    val celular: String,
    val cpf: String,
    val cidade: String,
    val estado: String,
    val id_gerente: Long,
    val porcentual_venda: Double
)