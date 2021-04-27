package br.com.devtec.bingo.dominio.cliente.exception

class PersistirDadosException(private val error: String): Exception("erro ao persistir os valores no banco erro: $error")