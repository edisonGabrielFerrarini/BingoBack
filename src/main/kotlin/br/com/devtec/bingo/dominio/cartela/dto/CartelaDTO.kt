package br.com.devtec.bingo.dominio.cartela.dto

data class CartelaDTO(
    val id: Long,
    val numeros_sorteados: String?,
    val ativa: Boolean,
    val valor: Double,
    val valor_numero: Double,
    val valor_porcentagem: Int
)

data class CartelaRendimentosDTO(
    val id: Long,
    val valor: Double,
    val rendimentos: Double,
    val valor_numero: Double

)

data class CartelaNumerosDTO(
    val numeros: String
)