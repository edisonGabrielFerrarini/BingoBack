package br.com.devtec.bingo.dominio.cartela.dto

data class CartelaDTO(
    val id: Long,
    val numeros_sorteados: String?,
    val ativa: Boolean,
    val valor: Double,
    val valor_numero: Double,
    val valor_porcentagem: Int
)