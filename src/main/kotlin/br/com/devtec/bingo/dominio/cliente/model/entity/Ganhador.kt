package br.com.devtec.bingo.dominio.cliente.model.entity

import br.com.devtec.bingo.dominio.ticket.model.entity.Ticket
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ganhador")
data class Ganhador(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long,

    @OneToOne(fetch = FetchType.LAZY)
    val ticket: Ticket,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)