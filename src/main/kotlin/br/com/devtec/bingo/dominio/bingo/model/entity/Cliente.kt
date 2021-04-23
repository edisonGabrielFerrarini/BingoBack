package br.com.devtec.bingo.dominio.bingo.model.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    var id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    val usuario: Usuario,

    @OneToOne(fetch = FetchType.LAZY)
    val agente: Agente,

    @Column(length = 60, nullable = false)
    val nome: String,

    @Column(length = 20, nullable = false)
    val cpf: String,

    @Column(nullable = true)
    val ganhos: Double,

    @Column(nullable = true)
    val saldo: Double,

    @Column(length = 30, nullable = true)
    val telefone: String,

    @Column(length = 30, nullable = false)
    val celular: String,

    @Column(length = 150, nullable = true)
    val cidade: String,

    @Column(length = 150, nullable = true)
    val bairro: String,

    @Column(length = 20, nullable = true)
    val cep: String,

    @Column(length = 100, nullable = true)
    val estado: String,

    @CreatedDate
    @Column(nullable = true)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = true)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)