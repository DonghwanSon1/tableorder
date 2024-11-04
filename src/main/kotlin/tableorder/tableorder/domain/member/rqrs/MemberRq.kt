package tableorder.tableorder.domain.member.rqrs

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import tableorder.tableorder.domain.member.Role
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemberRq(

    @Schema(description = "회원 sn")
    val sn: Long? = null,

    @field:NotBlank
    @JsonProperty("id")
    @Schema(description = "회원 id")
    private val _id: String?,

    @field:NotBlank
    @field:Pattern(
        regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력해주세요"
    )
    @JsonProperty("password")
    @Schema(description = "회원 비밀번호")
    private val _password: String?,

    @field:NotBlank
    @JsonProperty("name")
    @Schema(description = "회원 이름")
    private val _name: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = "날짜형식(YYYY-MM-DD)을 확인해주세요"
    )
    @Schema(description = "회원 생년월일")
    @JsonProperty("birthDate")
    private val _birthDate: String?,

    @field:NotBlank
    @field:Email
    @JsonProperty("email")
    private val _email: String?,
) {
    val id: String
        get() = _id!!
    val password: String
        get() = _password!!
    val name: String
        get() = _name!!
    val birthDate: LocalDate
        get() = _birthDate!!.toLocalDate()
    val email: String
        get() = _email!!
    private fun String.toLocalDate(): LocalDate =
            LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
