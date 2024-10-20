package tableorder.tableorder.domain.menu.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class MenuDetailRs(

    @Schema(description = "메뉴 sn")
    val sn: Long? = null,

    @Schema(description = "메뉴 이름")
    val name: String? = null,

    @Schema(description = "메뉴 설명")
    val description: String? = null,

    @Schema(description = "메뉴 가격")
    val price: Long? = null,

    @Schema(description = "메뉴 이미지")
    val imageUrl: String? = null,

    @Schema(description = "카테고리 생성일")
    val createdAt: LocalDateTime? = null,

    @Schema(description = "카테고리 수정일")
    val updatedAt: LocalDateTime? = null,

)
