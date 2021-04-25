package br.com.devtec.bingo.dominio.cliente.utils

enum class EnumCliente(val erro: String) {
    ERRO_AO_PERSISTIR_DADOS("erro ao salvar no banco de dados"),
    CLIENTE_NAO_ENCONTRADO("cliente não encontrado")
}