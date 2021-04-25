package br.com.devtec.bingo.dominio.cartela.utils

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class GeradorNumeros {
    fun gerarNumeros(): List<Int> {
        val list: MutableList<Int> = arrayListOf()
        while (list.size < 20){
            var numero = Random.nextInt(1,60)
            var verificar = true
            while (verificar){
                if (!list.contains(numero)){
                    verificar = false
                }else {
                    numero = Random.nextInt(1,60)
                }
            }
            list.add(numero)
        }
        return list.sorted()
    }
}