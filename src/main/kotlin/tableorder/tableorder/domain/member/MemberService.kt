package tableorder.tableorder.domain.member


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tableorder.tableorder.common.authority.JwtTokenProvider
import tableorder.tableorder.common.authority.TokenInfo
import tableorder.tableorder.common.authority.UsernamePasswordRoleAuthenticationToken
import tableorder.tableorder.domain.category.Category
import tableorder.tableorder.domain.category.CategoryRepository
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRq
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRs
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode
import tableorder.tableorder.domain.member.rqrs.LoginRq
import tableorder.tableorder.domain.member.rqrs.MemberRq

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signUp(rq: MemberRq, role: Role): String {
        // ID 중복 검사
        var member: Member? = findId(rq.id!!, role)
        if (member != null) {
            throw CommonException(CommonExceptionCode.DUPLICATE_ID)
        }

        member = Member.createMember(rq, passwordEncoder.encode(rq.password), role)

        memberRepository.save(member)
        return if (role == Role.SHOP) { "관리자 회원가입이 완료되었습니다." }
        else { "사용자 회원가입이 완료되었습니다." }
    }

    fun findId(id: String, role: Role): Member? {
        return memberRepository.findByIdAndRole(id, role)
    }

    // TODO Role도 같이 전달하기, 포스트맨 토큰 저장하도록 설정하기
    fun login(loginRq: LoginRq, role: Role): TokenInfo {
        val authenticationToken = UsernamePasswordRoleAuthenticationToken(
                loginRq.id, loginRq.password, role)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)

    }


}