package br.com.devtec.bingo.dominio.gerente.business

import br.com.devtec.bingo.dominio.gerente.dto.GerenteDTO
import br.com.devtec.bingo.dominio.gerente.dto.GerenteResponseDTO
import br.com.devtec.bingo.dominio.gerente.dto.converter.toDTO
import br.com.devtec.bingo.dominio.gerente.dto.converter.toEntiy
import br.com.devtec.bingo.dominio.gerente.dto.converter.toResponseDTO
import br.com.devtec.bingo.dominio.gerente.model.entity.Gerente
import br.com.devtec.bingo.dominio.gerente.model.repository.GerenteRepository
import br.com.devtec.bingo.dominio.utils.exception.PersistirDadosException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class GerenteBusiness(
    @Autowired val gerenteRepository: GerenteRepository
) {

    fun create(gerenteDTO: GerenteDTO): Gerente {
        try {
            return gerenteRepository.save(gerenteDTO.toEntiy())
        }catch (e: Exception){
            throw PersistirDadosException("erro ao salvar")
        }
    }

    fun getAll(pageable: Pageable): Page<GerenteResponseDTO> {
        try {
            return gerenteRepository.findAll(pageable).map {
                it.toResponseDTO()
            }
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao buscar gerentes")
        }
    }

    fun getById(id: Long): Optional<Gerente> {
        return gerenteRepository.findById(id)
    }

    fun getByCPF(cpf: String): ResponseEntity<GerenteDTO> {
        try {
            return ResponseEntity.accepted().body(gerenteRepository.findByCpf(cpf).toDTO())
        }catch (e: Exception){
            throw PersistirDadosException("Erro ao buscar cpf ${cpf}")
        }
    }


}