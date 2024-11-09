package tableorder.tableorder.common.authority

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig (
        private val jwtTokenProvider: JwtTokenProvider,
        // 추후 고도화 해야함.
        @Lazy private val customAuthenticationProvider: CustomAuthenticationProvider) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers("/member/**").anonymous()
//                    .requestMatchers("/category/**").hasAuthority("USER")
                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                    JwtAuthenticationFilter(jwtTokenProvider),
                    UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        val auth = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        auth.authenticationProvider(customAuthenticationProvider)
        return auth.build()
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder()
}