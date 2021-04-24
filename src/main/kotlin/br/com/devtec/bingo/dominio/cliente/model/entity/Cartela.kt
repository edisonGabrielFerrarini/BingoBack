package br.com.devtec.bingo.dominio.cliente.model.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "cartela")
data class Cartela(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long,

    @Column(nullable = false)
    val numeros_sorteados: String,

    @Column(nullable = false)
    val ativa: Boolean,

    @Column(nullable = false)
    val valor: Double,

    @Column(nullable = false)
    val valor_numero: Double,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)