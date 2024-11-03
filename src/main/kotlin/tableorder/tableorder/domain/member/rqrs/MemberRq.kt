package tableorder.tableorder.domain.member.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import tableorder.tableorder.domain.member.Role
import java.time.LocalDate

data class MemberRq(
    @Schema(description = "회원 sn")
    val sn: Long? = null,

    @Schema(description = "회원 id")
    val id: String? = null,

    @Schema(description = "회원 비밀번호")
    val password: String? = null,

    @Schema(description = "회원 이름")
    val name: String? = null,

    @Schema(description = "회원 생년월일")
    val birthDate: LocalDate? = null,

//    @Schema(description = "회원 역할")
//    val role: Role? = null,

    @Schema(description = "회원 이메일")
    val email: String? = null,
)
