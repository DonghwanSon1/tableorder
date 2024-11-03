package tableorder.tableorder.domain.member

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import tableorder.tableorder.common.BaseResponse
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode
import tableorder.tableorder.domain.member.rqrs.MemberRq

@RestController
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 가입 관련 API")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/shop-admin/check-id/{id}")
    @Operation(summary = "가게 관리자 ID 조회", description = "중복체크를 위한 가게 관리자 ID 조회합니다.")
    fun findShopAdminId(@PathVariable id: String): BaseResponse<Unit>{
        val member: Member? = memberService.findShopAdminId(id, Role.SHOP_ADMIN.name)
        return if (member != null) { BaseResponse(message = "중복된 아이디가 있습니다.") }
        else { BaseResponse(message = "사용 가능한 아이디 입니다.") }
    }

    @PostMapping("/shop-admin/sign")
    @Operation(summary = "가게 관리자 회원가입", description = "가게의 관리자 회원가입 합니다.")
    fun signUpShopAdmin(@RequestBody rq: MemberRq): BaseResponse<Unit>{
        val message: String = memberService.signUp(rq, Role.SHOP_ADMIN.name)
        return BaseResponse(message = message)
    }

}