package br.com.devtec.bingo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BingoApplication

fun main(args: Array<String>) {
	runApplication<BingoApplication>(*args)
}
