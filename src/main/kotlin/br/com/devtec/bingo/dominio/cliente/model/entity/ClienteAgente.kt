package br.com.devtec.bingo.dominio.cliente.model.entity

import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "cliente_agente")
data class ClienteAgente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    var gerente: Gerente,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)