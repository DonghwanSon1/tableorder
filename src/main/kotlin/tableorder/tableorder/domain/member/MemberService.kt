package tableorder.tableorder.domain.member


import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tableorder.tableorder.domain.category.Category
import tableorder.tableorder.domain.category.CategoryRepository
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRq
import tableorder.tableorder.domain.menu.rqrs.MenuDetailRs
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode
import tableorder.tableorder.domain.member.rqrs.MemberRq

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
) {

    // TODO JWT/Security 만들기, 테블릿 회원가입도 만들기,
    @Transactional
    fun signUp(rq: MemberRq, role: Role): String {
        // ID 중복 검사
        var member: Member? = findId(rq.id!!, role)
        if (member != null) {
            throw CommonException(CommonExceptionCode.DUPLICATE_ID)
        }

        member = Member.createMember(rq, role)
        memberRepository.save(member)
        return if (role == Role.SHOP) { "관리자 회원가입이 완료되었습니다." }
        else { "사용자 회원가입이 완료되었습니다." }
    }

    fun findId(id: String, role: Role): Member? {
        return memberRepository.findByIdAndRole(id, role)
    }


}