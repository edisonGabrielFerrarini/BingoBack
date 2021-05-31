package br.com.devtec.bingo.dominio.gerente.dto

data class GerenteDTO(
    val nome: String,
    val telefone: String,
    val celular: String,
    val cpf: String,
    val cidade: String,
    val estado: String
)

data class GerenteResponseDTO(
    val id: Long,
    val nome: String,
    val telefone: String,
    val celular: String,
    val cpf: String,
    val cidade: String,
    val estado: String
)