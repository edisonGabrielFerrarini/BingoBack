package br.com.devtec.bingo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
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

    @Autowired
    private lateinit var env: Environment

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .httpBasic().and().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/users/cadastro").permitAll()
            .antMatchers(HttpMethod.POST, "/api/users/login").hasAnyRole("USER")
            .antMatchers(HttpMethod.POST, "/api/users/login_admin").hasAnyRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/cartela").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.POST, "/api/cartela").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/cartela/inativa").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/cartela/sorteia").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/cartela/rendimento").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/cartela/all").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.GET, "/api/cartela/ultimo").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.GET, "/api/cartela/cancela").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/agente").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/agente").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/agente/id/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/agente/cpf/{cpf}").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/gerente").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/gerente").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/gerente/id/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/gerente/cpf/{cpf}").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/vincula/cliente/{id_cliente}/agente/{id_agente}").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/ganhador").hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.GET, "/api/ticket/busca/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/ticket").hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/ticket").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.GET, "/api/cliente").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/cliente/update/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/cliente/busca/{id}").hasRole("USER")
            .antMatchers(HttpMethod.GET, "/api/cliente/admin/busca/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/cliente/update/saldo/{id}").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/cliente/debita/saldo/{id}").hasRole("ADMIN")
            .and().csrf().disable()
            .formLogin().disable()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration().applyPermitDefaultValues()
        configuration.addAllowedOrigin(env.getProperty("origin.permitida").toString())
        configuration.allowedMethods = listOf("POST", "GET", "PUT", "DELETE", "OPTIONS")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }


    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/materialize/**", "/style/**")
    }
}