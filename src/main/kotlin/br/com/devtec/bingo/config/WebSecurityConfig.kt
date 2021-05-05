package br.com.devtec.bingo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userDetailsService: ImplementsUserDetailsService

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .httpBasic().and().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/users/cadastro").permitAll()
            .antMatchers(HttpMethod.GET, "/api/cartela").permitAll()
            .antMatchers(HttpMethod.POST, "/api/cartela").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/cartela/inativa").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/cartela/sorteia").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/ganhador").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/ticket/busca/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/ticket").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/ticket").permitAll()
            .antMatchers(HttpMethod.GET, "/api/cliente").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/cliente/update/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/cliente/busca/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/cliente/admin/busca/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/cliente/update/saldo/{id}").hasRole("ADMIN")
            .and().csrf().disable()
            .formLogin().disable()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(BCryptPasswordEncoder())
    }



    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/materialize/**", "/style/**")
    }
}