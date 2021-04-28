package br.com.devtec.bingo.dominio.ganhador.facade

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.ganhador.business.GanhadorBusiness
import br.com.devtec.bingo.dominio.ganhador.model.entity.Ganhador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GanhadorFacade {

    @Autowired
    lateinit var ganhadorBusiness: GanhadorBusiness

    fun create(numerosSorteados: String, cartela: Cartela): List<Ganhador> {
        return ganhadorBusiness.create(numerosSorteados, cartela)
    }

}