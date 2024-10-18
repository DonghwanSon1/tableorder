package tableorder.tableorder.domain.menu.rqrs

import io.swagger.v3.oas.annotations.media.Schema
import tableorder.tableorder.domain.category.Category
import tableorder.tableorder.domain.menu.Menu
import java.time.LocalDateTime

data class MenuRs(

    @Schema(description = "카테고리 sn")
    val categorySn: Long? = null,

    @Schema(description = "카테고리 이름")
    val categoryName: String? = null,

    @Schema(description = "메뉴")
    val menuList: List<MenuDetailRs>? = null,

){
    companion object {
        fun createMenuRs(category: Category, menuList: List<MenuDetailRs>?): MenuRs {
            return MenuRs(
                    categorySn = category.sn,
                    categoryName = category.name,
                    menuList = menuList
            )
        }
    }
}

