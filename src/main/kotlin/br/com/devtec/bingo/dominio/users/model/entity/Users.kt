package br.com.devtec.bingo.dominio.users.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @CreatedDate
    @Column(nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now(),

    @CreatedDate
    @Column(nullable = false)
    val updated_at: LocalDateTime = LocalDateTime.now(),
)