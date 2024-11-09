package tableorder.tableorder.common.authority

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UsernameNotFoundException
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode
import tableorder.tableorder.domain.member.Member
import tableorder.tableorder.domain.member.MemberRepository
import tableorder.tableorder.domain.member.Role

@Service
class CustomUserDetailsService(
        private val memberRepository: MemberRepository
) {

    // 기존의 loadUserByUsername을 사용하지 않고 직접 구현하는 메서드
    fun loadUserByUsernameAndRole(username: String, role: Role): UserDetails {
        val member = memberRepository.findByIdAndRole(username, role)
                ?: throw CommonException(CommonExceptionCode.USER_NAME_NOT_FOUND)

        return User(
                member.id,
                member.password,
                listOf(SimpleGrantedAuthority("${member.role ?: "USER"}"))
        )
    }
}
