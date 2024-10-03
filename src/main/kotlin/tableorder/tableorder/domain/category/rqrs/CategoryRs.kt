package tableorder.tableorder.domain.category.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import java.time.LocalDateTime

data class CategoryRs(

    @Schema(description = "카테고리 sn")
    val sn: Long? = null,

    @Schema(description = "카테고리 이름")
    val name: String? = null,

    @Schema(description = "카테고리 생성일")
    val createdAt: LocalDateTime? = null,

    @Schema(description = "카테고리 수정일")
    val updatedAt: LocalDateTime? = null,

)
