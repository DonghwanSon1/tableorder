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

    // TODO 해당 회원가입 테스트, JWT/Security 만들기, 테블릿 회원가입도 만들기,
    @Transactional
    fun signUp(rq: MemberRq, role: String): String {
        // ID 중복 검사
        var member: Member? = findShopAdminId(rq.id!!, role)
        if (member != null) {
            throw CommonException(CommonExceptionCode.DUPLICATE_ID)
        }

        member = Member.createMember(rq)
        memberRepository.save(member)
        return if (role.equals(Role.SHOP_ADMIN)) { "관리자 회원가입이 완료되었습니다." }
        else { "사용자 회원가입이 완료되었습니다." }
    }

    fun findShopAdminId(id: String, role: String): Member? {
        return memberRepository.findByIdAndRole(id, role)
    }


}