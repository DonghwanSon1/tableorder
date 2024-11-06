package tableorder.tableorder.domain.member.rqrs

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import tableorder.tableorder.domain.member.Role
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class LoginRq(

    @field:NotBlank
    @JsonProperty("id")
    @Schema(description = "회원 id")
    private val _id: String?,

    @field:NotBlank
    @JsonProperty("password")
    @Schema(description = "회원 비밀번호")
    private val _password: String?,
) {
    val id: String
        get() = _id!!
    val password: String
        get() = _password!!
}
