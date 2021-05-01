package br.com.devtec.bingo.security.services

import br.com.devtec.bingo.dominio.users.model.entity.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val user: Users) : UserDetails {
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun isEnabled() = true

    override fun getUsername() = user.email

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}