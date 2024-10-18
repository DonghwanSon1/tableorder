package tableorder.tableorder.domain.menu

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tableorder.tableorder.domain.category.rqrs.CategoryRq
import tableorder.tableorder.domain.category.rqrs.CategoryRs
import tableorder.tableorder.domain.menu.rqrs.MenuRq
import tableorder.tableorder.domain.menu.rqrs.MenuRs
import tableorder.tableorder.global.exception.CommonException
import tableorder.tableorder.global.exception.CommonExceptionCode

@RestController
@RequestMapping("/menu")
@Tag(name = "Menu", description = "메뉴 관련 API")
class MenuController(
    private val menuService: MenuService
) {
    @PostMapping("{categorySn}")
    @Operation(summary = "메뉴 다중 저장", description = "카테고리에 속한 메뉴들을 다중 저장합니다.")
    fun saveMenu(@PathVariable categorySn: Long,
                    @RequestBody menuRqList: List<MenuRq>) {
        // 비워있거나 Null로 들어올 시 Exception 발생
        if (menuRqList.isNullOrEmpty()) throw CommonException(CommonExceptionCode.BAD_REQUEST)
        menuService.saveMenu(categorySn, menuRqList)
    }

    @GetMapping("{categorySn}")
    @Operation(summary = "메뉴 조회", description = "카테고리에 속한 메뉴 조회합니다.")
    fun searchMenu(@PathVariable categorySn: Long): MenuRs {
        return menuService.searchMenu(categorySn)
    }

}