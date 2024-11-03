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
    @PostMapping("{categorySn}")
    @Operation(summary = "메뉴 다중 저장", description = "카테고리에 속한 메뉴들을 다중 저장합니다.")
    fun saveMenu(@PathVariable categorySn: Long,
                    @RequestBody menuRq: MenuRq) {
        // 비워있거나 Null로 들어올 시 Exception 발생
        if (menuRq.menuDetailList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        menuService.saveMenu(categorySn, menuRq)
    }

    @GetMapping("{categorySn}")
    @Operation(summary = "메뉴 조회", description = "카테고리에 속한 메뉴 조회합니다.")
    fun searchMenu(@PathVariable categorySn: Long): MenuRs {
        return menuService.searchMenu(categorySn)
    }

    @PatchMapping("{categorySn}")
    @Operation(summary = "메뉴 다중 수정", description = "메뉴를 다중 수정합니다.")
    fun updateMenu(@PathVariable categorySn: Long,
                   @RequestBody menuRq: MenuRq) {
        // menuDetailList 와 삭제할 메뉴들이 비워있거나 Null로 들어올 시 Exception 발생
        if (menuRq.menuDetailList.isNullOrEmpty() && menuRq.deleteSnList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        menuService.saveMenu(categorySn, menuRq)
    }

    @DeleteMapping("{categorySn}")
    @Operation(summary = "카테고리 속한 메뉴 전체 삭제", description = "카테고리에 속한 메뉴 전체를 완전 삭제합니다.")
    fun deleteAllMenu(@PathVariable categorySn: Long,) {
        menuService.deleteAllMenu(categorySn)
    }

}