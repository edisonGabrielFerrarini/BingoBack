package br.com.devtec.bingo.dominio.utils.exception

class PersistirDadosException(private val error: String): Exception("erro ao persistir os valores no banco erro: $error")