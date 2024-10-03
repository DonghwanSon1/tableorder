package tableorder.tableorder.domain.category.rqrs

import io.swagger.v3.oas.annotations.media.Schema

data class CategoryRq(

    @Schema(description = "카테고리 sn")
    val sn: Long? = null,

    @Schema(description = "카테고리 이름")
    val name: String? = null,

    @Schema(description = "카테고리 순서")
    val order: Long? = null
)
