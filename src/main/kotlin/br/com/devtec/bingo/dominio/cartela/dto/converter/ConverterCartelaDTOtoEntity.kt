package br.com.devtec.bingo.dominio.cartela.dto.converter

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela

fun CartelaDTO.toEntity() = Cartela(
    numeros_sorteados = numeros_sorteados,
    ativa = true,
    valor = valor,
    valor_numero = valor_numero,
    acumulada = false
)

fun CartelaDTO.toInativeEntity(id: Long) = Cartela(
    id = id,
    numeros_sorteados = numeros_sorteados,
    ativa = false,
    valor = valor,
    valor_numero = valor_numero,
    acumulada = false
)