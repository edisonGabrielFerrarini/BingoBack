package br.com.devtec.bingo.dominio.ticket.model.entity

import br.com.devtec.bingo.dominio.cartela.model.entity.Cartela
import br.com.devtec.bingo.dominio.cliente.model.entity.Cliente
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ticket")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    val cliente: Cliente,

    @OneToOne(fetch = FetchType.LAZY)
    val cartela: Cartela,

    @Column(length = 100, nullable = false)
    val numeros: String,

    @Column(nullable = false)
    val valor: Double,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)