package tableorder.tableorder.common.authority

import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode

@Component
class CustomAuthenticationProvider(
        // 추후 고도화 해야함.
        @Lazy private val customUserDetailsService: CustomUserDetailsService,
        private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()
        val role = (authentication as UsernamePasswordRoleAuthenticationToken).role

        val userDetails = customUserDetailsService.loadUserByUsernameAndRole(username, role)

        if (!passwordEncoder.matches(password, userDetails.password)) throw CommonException(CommonExceptionCode.INVALID_PASSWORD)

        return UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordRoleAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}