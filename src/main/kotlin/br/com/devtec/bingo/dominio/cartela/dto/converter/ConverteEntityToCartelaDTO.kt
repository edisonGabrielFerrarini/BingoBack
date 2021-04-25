package br.com.devtec.bingo.dominio.cartela.dto.converter

import br.com.devtec.bingo.dominio.cartela.dto.CartelaDTO
import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela

fun Cartela.toDTO() = CartelaDTO(
    numeros_sorteados = numeros_sorteados,
    ativa = true,
    valor = valor,
    valor_numero = valor_numero
)