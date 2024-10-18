package tableorder.tableorder.domain.menu.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import tableorder.tableorder.domain.category.Category
import tableorder.tableorder.domain.menu.Menu
import java.time.LocalDateTime

data class MenuRq(

    @Schema(description = "메뉴 sn")
    val sn: Long? = null,

    @Schema(description = "메뉴 이름")
    val name: String? = null,

    @Schema(description = "메뉴 순서")
    val order: Long? = null,

    @Schema(description = "메뉴 이미지")
    val imageUrl: String? = null,
)
