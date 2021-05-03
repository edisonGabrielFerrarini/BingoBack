package br.com.devtec.bingo.dominio.users.model.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "roles")
class Roles(

    @Id
    val nomeRole: String


) : GrantedAuthority {
    override fun getAuthority(): String {
        return this.nomeRole
    }
}