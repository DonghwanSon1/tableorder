package tableorder.tableorder.domain.menu.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import tableorder.tableorder.domain.category.Category
import tableorder.tableorder.domain.menu.Menu
import java.time.LocalDateTime

data class MenuRq(

    @Schema(description = "삭제할 메뉴 snList")
    val deleteSnList: List<Long>? = null,

    @Schema(description = "메뉴 Detail")
    val menuDetailList: List<MenuDetailRq>? = null
)
