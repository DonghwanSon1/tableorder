package tableorder.tableorder.common.authority

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException
import tableorder.tableorder.domain.member.Member
import tableorder.tableorder.domain.member.MemberRepository

@Service
class CustomUserDetailsService(
        private val memberRepository: MemberRepository,
        private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
            memberRepository.findById(username)
                    ?.let { createUserDetails(it) }
                    ?: throw UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.")

    private fun createUserDetails(member: Member): UserDetails =
            User(
                    member.id,
                    passwordEncoder.encode(member.password),
                    listOf(SimpleGrantedAuthority("ROLE_${member.role ?: "USER"}"))
            )
}
