package tableorder.tableorder.domain.member

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import tableorder.tableorder.common.BaseResponse
import tableorder.tableorder.common.authority.TokenInfo
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode
import tableorder.tableorder.domain.member.rqrs.LoginRq
import tableorder.tableorder.domain.member.rqrs.MemberRq

@RestController
@RequestMapping("/member")
@Tag(name = "Member", description = "회원 가입 관련 API")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/check-id")
    @Operation(summary = "중복체크를 위한 ID 조회", description = "중복체크를 위한 ID 조회합니다.")
    fun findId(@RequestParam id: String, @RequestParam role: String): BaseResponse<Unit> {
        val roleEnum = Role.values().find { it.name == role.uppercase() } ?: throw CommonException(CommonExceptionCode.NO_ROLE)
        val member: Member? = memberService.findId(id, roleEnum)
        return if (member != null) { BaseResponse(message = "중복된 아이디가 있습니다.") }
        else { BaseResponse(message = "사용 가능한 아이디 입니다.") }
    }

    @PostMapping("/shop/sign")
    @Operation(summary = "가게 관리자 회원가입", description = "가게의 관리자 회원가입 합니다.")
    fun signUpShop(@RequestBody @Valid rq: MemberRq): BaseResponse<Unit> {
        val message: String = memberService.signUp(rq, Role.SHOP)
        return BaseResponse(message = message)
    }

    @PostMapping("/user/sign")
    @Operation(summary = "일반 사용자 회원가입", description = "일반 사용자 회원가입 합니다.")
    fun signUpUser(@RequestBody @Valid rq: MemberRq): BaseResponse<Unit> {
        val message: String = memberService.signUp(rq, Role.USER)
        return BaseResponse(message = message)
    }
    @PostMapping("/shop/login")
    @Operation(summary = "가게의 관리자 로그인", description = "가게의 관리자 로그인을 합니다.")
    fun shopLogin(@RequestBody @Valid loginRq: LoginRq): TokenInfo {
        return memberService.login(loginRq, Role.SHOP)
    }

    @PostMapping("/user/login")
    @Operation(summary = "일반 사용자 로그인", description = "일반 사용자 로그인을 합니다.")
    fun userLogin(@RequestBody @Valid loginRq: LoginRq): TokenInfo {
        return memberService.login(loginRq, Role.USER)
    }



}