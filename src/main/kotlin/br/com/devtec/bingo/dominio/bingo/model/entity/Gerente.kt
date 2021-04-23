package br.com.devtec.bingo.dominio.bingo.model.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "gerente")
data class Gerente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long,

    @OneToOne(fetch = FetchType.LAZY)
    val usuario: Usuario,

    @Column(length = 60, nullable = false)
    val nome: String,

    @Column(length = 30, nullable = false)
    val telefone: String,

    @Column(length = 30, nullable = false)
    val celular: String,

    @Column(length = 20, nullable = false)
    val cpf: String,

    @Column(length = 150, nullable = false)
    val cidade: String,

    @Column(length = 150, nullable = false)
    val bairro: String,

    @Column(length = 20, nullable = false)
    val cep: String,

    @Column(length = 100, nullable = false)
    val estado: String,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)