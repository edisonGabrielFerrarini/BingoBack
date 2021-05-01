package br.com.devtec.bingo.dominio.cliente.model.entity

import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "agente")
data class Agente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    val users: Users,

    @OneToOne(fetch = FetchType.LAZY)
    var gerente: Gerente,

    @Column(length = 60, nullable = false)
    val nome: String,

    @Column(length = 30, nullable = false)
    val telefone: String,

    @Column(length = 30, nullable = false)
    val celular: String,

    @Column(length = 20, nullable = false)
    val cpf: String,

    @Column(nullable = true)
    val porcentual_venda: Double,

    @Column(length = 150, nullable = false)
    val cidade: String,

    @Column(length = 100, nullable = false)
    val estado: String,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)