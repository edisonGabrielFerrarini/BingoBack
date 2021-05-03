package br.com.devtec.bingo.config

import br.com.devtec.bingo.dominio.users.model.entity.Users
import br.com.devtec.bingo.dominio.users.model.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException

import org.springframework.security.core.userdetails.UserDetails

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional
class ImplementsUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val usuario = userRepository.findByEmail(email) ?: throw UsernameNotFoundException("Usuario não encontrado!")
        return Users(usuario.id, usuario.email, usuario.senha, usuario.roles,usuario.created_at, usuario.updated_at)
    }
}