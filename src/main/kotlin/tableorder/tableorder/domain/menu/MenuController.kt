package tableorder.tableorder.domain.menu

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.common.exception.CommonException
import tableorder.tableorder.common.exception.CommonExceptionCode

@RestController
@RequestMapping("/menu")
@Tag(name = "Menu", description = "메뉴 관련 API")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping("{categorySn}")
    @Operation(summary = "메뉴 조회", description = "카테고리에 속한 메뉴 조회합니다.")
    fun searchMenu(@PathVariable categorySn: Long): MenuRs {
        return menuService.searchMenu(categorySn)
    }

}